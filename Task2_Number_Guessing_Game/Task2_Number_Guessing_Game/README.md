# 🎯 Number Guessing Game

A Java-based Number Guessing Game developed as part of the **OIBSIP (Oasis Infobyte Internship Program) – Task 2**.

## 📌 Objective

The objective of this project is to develop an interactive number guessing game where the computer generates a random number and the player attempts to guess it within a limited number of attempts.

The game provides **Too High**, **Too Low**, and **Correct** hints after each valid guess.

## ✨ Features

* 🎲 Random number generation
* 🎯 Number guessing from the selected range
* 💡 Too High / Too Low hints
* 🔢 Attempt counter
* ⏳ Maximum attempt limit
* ❌ You Lost message with the correct number revealed
* 🔄 Play Again option
* 📊 Multiple-round score summary
* 🎚️ Three difficulty levels:

  * Easy — 1 to 50, 10 attempts
  * Medium — 1 to 100, 7 attempts
  * Hard — 1 to 200, 5 attempts
* 🛡️ Input validation for invalid and out-of-range values

## 🛠️ Technologies Used

* **Java**
* **Java Random**
* **Java Scanner**
* **Java ArrayList**
* Core Java concepts:

  * Loops
  * Conditional statements
  * Variables
  * User input
  * Random number generation
  * Collections

## ⚙️ How the Game Works

1. The player selects a difficulty level.
2. The system generates a random number according to the selected range.
3. The player enters a guess.
4. The game compares the guess with the secret number.
5. The player receives a hint:

   * **Too High!** — guessed number is greater than the secret number.
   * **Too Low!** — guessed number is smaller than the secret number.
   * **Correct!** — the player has guessed the number.
6. The attempt counter is updated after every valid guess.
7. If the maximum attempts are reached, the player loses and the correct number is revealed.
8. The player can start another round.
9. At the end, the game displays a summary of all rounds played.

## 🎮 Difficulty Levels

| Difficulty | Number Range | Maximum Attempts |
| ---------- | ------------ | ---------------- |
| Easy       | 1 – 50       | 10               |
| Medium     | 1 – 100      | 7                |
| Hard       | 1 – 200      | 5                |

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

### 2. Open the project folder

Navigate to:

```text
Task2_Number_Guessing_Game
```

### 3. Compile the Java program

```bash
javac NumberGuessingGame.java
```

### 4. Run the program

```bash
java NumberGuessingGame
```

## 📷 Screenshots

Screenshots demonstrating the game execution will be added here.

## 🧪 Testing

The application was tested with:

* Correct guesses
* Too High guesses
* Too Low guesses
* Maximum attempts exceeded
* Invalid non-numeric input
* Out-of-range input
* Multiple rounds
* Different difficulty levels
* Play Again functionality

## 📚 Learning Outcomes

Through this project, I practiced:

* Java programming fundamentals
* Random number generation
* User input handling
* Loops and conditional logic
* Input validation
* ArrayList collections
* Building a complete console-based application

## 🚀 Future Enhancements

* Graphical User Interface using Java Swing
* Persistent high-score system
* Timer-based challenge mode
* Leaderboard functionality

## 👩‍💻 Internship

**OIBSIP – Oasis Infobyte Internship Program**

**Task 2: Number Guessing Game**

## 📄 License

This project is created for educational and internship purposes.
