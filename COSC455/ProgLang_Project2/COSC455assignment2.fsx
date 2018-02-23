open System.Security.Cryptography.X509Certificates


type token =
           | Comma of string
           | Adj of string
           | Adv of string
           | Conj of string
           | Prep of string
           | Noun of string
           | Verb of string
           | Art of string
           | EOS
           | NONE

let tokenFromLexeme str =
    match str with
       | "," -> Comma str
       | "fast" | "slow" | "furry" | "yellow" -> Adj str
       | "quickly" | "slowly" | "quietly" -> Adv str
       | "of" | "around" -> Prep str
       | "and" | "or" -> Conj str
       | "dog" | "cat" | "tree" | "cheese" | "mouse" -> Noun str
       | "a" | "an" | "the" -> Art str
       | "chases" -> Verb str
       | "." -> EOS
       | _ -> NONE


let rec parse sentence =
    [] @ (nounPhrase sentence)


and nounPhrase = function
    | Art(x) :: (Noun(_) as nou) :: xs -> [sprintf "Article: %s" x] @ noun (nou :: xs)
    | Art(x) :: xs ->
        [sprintf "Article: %s" x] @ adjList xs
    | _ -> [sprintf "%s" "Expecting Article"]

and adjList = function
    | Adj(x) :: (Comma(_) as com) :: xs ->
           [sprintf "Adjective followed by comma: %s" x] @
           adjTail (com :: xs)
    | Adj(x) :: xs -> [sprintf "Adjective: %s" x] @ noun xs
    | _ -> [sprintf "Expecting Adjective: "]

and adjTail = function 
    | Comma(x) :: xs -> 
           [sprintf "Comma: %s" x] @ adjList xs
    | _ -> [sprintf "Expecting Comma"]
     
and noun = function
    | Noun(x) :: EOS(_) :: xs ->
           [sprintf "Noun and Done: %s" x]
    | Noun(x) :: (Prep(_) as prep) :: xs -> 
           [sprintf "Noun followed by Prep Phrase: %s" x] @
           prepPhrase (prep :: xs)
    | Noun(x) :: xs -> [sprintf "Noun: %s" x] @ verbPhrase xs
    | _ -> [sprintf "%s" "Expecting noun"]

and adjective = function
    | Adj(x) :: xs ->
        [sprintf "Adjective: %s" x] @ noun xs
    | _ -> [sprintf "%s" "Expecting Adjective"]

and verbPhrase = function
    | Adv(x) :: xs -> [sprintf "Adverb Followed by Verb: %s" x] @ verbPhrase xs
    | Verb(x) :: xs ->
        [sprintf "Verb: %s" x] @ nounPhrase xs
    | _ -> [sprintf "%s" "Expecting Adverb or Verb"]

and prepPhrase = function
    | Prep(x) :: xs -> [sprintf "Preposition: %s" x] @ nounPhrase xs
    | _ -> [sprintf "%s" "Expecting Preposition"]

System.Console.Write("Input Sentence: ")

let input = System.Console.ReadLine()

//let input = "the fast , furry dog slowly chases the slow cat around the tree ."

let tokens = input.Split()
            |> Array.toList
            |> List.map tokenFromLexeme

let parsedList = parse tokens


