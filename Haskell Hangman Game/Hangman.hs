import System.Random
import Data.List

main = do
    file <- readFile "words.txt"
    let fwordList = lines file
    let fword = getRandWord fwordList
    test <- fword

    let numSpaces = length test
    let guessWord = replicate (numSpaces - 1) '_'
    let guesses = ""

    let result = playHangman guesses test guessWord
    
    res <- result
    putStrLn $ intersperse ' ' res



getRandWord :: [a] -> IO a
getRandWord list = do 
    idx <- randomRIO (0, length list - 1)
    return $ list !! idx

-- fword = randomly chosen word from file
-- guessesIO = inputted guess string by player
-- guessWord = progress towards guessing the word that is a mix of blanks("_") and letters
playHangman :: [Char] -> String -> String -> IO String
playHangman guessesIO fword guessWord = 
    if (elem '_' guessWord) 
        then do putStrLn $ intersperse ' ' guessWord
                -- putStr "Please guess a letter: "
                guess <- getLine
                let guesses = guessesIO ++ guess
                playHangman guesses fword $ reconstructWord guesses guesses fword
        else do return (guessWord)

-- Displays _ if the letter has not been guessed. Displays the letter if the letter has been correctly guessed. 
-- (i.e. g _ _ s s _ s for word "glasses" if g and s have been guessed already)
-- The list of guessed letters(originalgs) needs to be passed as parameter everytime reconstructWord is called due to 
-- nature of functional language.
reconstructWord :: String -> String -> String -> String
reconstructWord originalgs _ [] = []
reconstructWord originalgs [] (x:xs)
        | xs == [] = []
        | otherwise = '_' : reconstructWord originalgs originalgs xs
reconstructWord originalgs (g:gs) (x:xs) = if (g == x)
    then g : reconstructWord originalgs originalgs xs
    else reconstructWord originalgs gs (x:xs)
        

-- replaceNth :: Char -> Int -> String -> String
-- replaceNth _ _ [] = []
-- replaceNth guess n (x:xs)
--             | n == 0 = guess:xs
--             | otherwise = x:replaceNth guess (n-1) xs 

-- charToString :: Char -> String
-- charToString = (:[])