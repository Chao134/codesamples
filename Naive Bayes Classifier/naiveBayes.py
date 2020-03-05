# naiveBayes.py
# -------------
# A sample of code containing the algorithm for a Naive Bayes classifier.
# Note: this is only a sample and does not contain enough for the code to be run properly.

import util
import classificationMethod
import math

class NaiveBayesClassifier(classificationMethod.ClassificationMethod):
      
  def trainAndTune(self, trainingData, trainingLabels, validationData, validationLabels, kgrid):
    # Find the non-conditional probability.
    P = util.Counter()
    for label in trainingLabels:
        P[label] += 1
    P.normalize()
    self.P = P

    # Creation and initialization
    counts = {}
    totals = {}
    for feature in self.features:
      counts[feature] = {0 : util.Counter(), 1 : util.Counter()}
      totals[feature] = util.Counter()

    
    # Get the number of occurrences in the training data.
    for i, datum in enumerate(trainingData):
      label = trainingLabels[i]
      for feature, value in datum.items():
        counts[feature][value][label] += 1
        totals[feature][label] += 1

    bestConditionalP = {}
    bestAccuracy = None

    # Find best k for Laplace smoothing to avoid overfitting
    for k in kgrid or [0.0]:
      correct = 0
      conditionalPs = {}
      for feature in self.features:
        conditionalPs[feature] = {0: util.Counter(), 1: util.Counter()}
      
      for feature in self.features:
        for value in [0,1]:
          for label in self.legalLabels:
            # counts[feature][value][label] is the number of times the pixel took value "feature" in the training set of label "l"
            conditionalPs[feature][value][label] = (counts[feature][value][label] + k) / (totals[feature][label] + 2 * k)
      
      self.conditionals = conditionalPs
      guesses = self.classify(validationData)
      for i, guess in enumerate(guesses):
        correct += (validationLabels[i] == guess)
      accuracy = correct / len(guesses)

      if bestAccuracy < accuracy or bestAccuracy is None:
        bestAccuracy = accuracy
        bestConditionalP = conditionalPs
        self.k = k
    self.conditionals = bestConditionalP
    

    
      
