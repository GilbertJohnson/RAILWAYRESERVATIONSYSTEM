<h1 align="center">🚆 Railway Reservation System</h1>
<p align="center">
  <i>A modern Java console simulation of the Railway ticket booking system — built with clean design, near real-world logic, and a fun twist 🎟️✨</i>
</p>
     
<p align="center">
<img src="https://img.shields.io/badge/Java-17%2B-orange?logo=java" alt="Java Version" />
<img src="https://img.shields.io/badge/Status-Active-brightgreen" alt="Project Status" />
<img src="https://img.shields.io/badge/Contributions-Welcome-pink" alt="Contributions" />
</p>

---

## 💡 Overview

**Railway Reservation System** is a Java-based console app that simulates a near real-world railway booking system - asked in zoho interviews.  
It handles confirmed seats, RAC, and waiting lists — prioritizing **senior citizens**, **woman with children**, and user **berth preferences**.

🎯 It’s an ideal project for beginners to understand **Java OOP**, **Collections**, and **logic design** while keeping it interactive and fun!

---

## 🧩 Project Structure

```
📦 RailwayReservationSystem/
├── model/
│   ├── Passenger.java                  # Passenger model
│   ├── StandardBirthCount.java         # Enum for berth capacities
|  
├── service/
│   ├── FormatWriter.java               # Simple formater for console
│   ├── RailwayReservationSystem.java   # Core logic for reservation and booking
│
├── Main.java                           # Runs the console application
├── README.md                           # You are here 🚀
```

---

## 🚀 Features

✨ Book tickets dynamically with real-time berth tracking  
🧓 Priority for senior citizens & parents with children  
🛏️ Intelligent berth allocation (L, M, U, SU)  
🎫 Auto-generated ticket IDs (e.g., `AB12CD`)  
🪑 RAC and Waiting List management  
🔄 Automatic upgrades after cancellation  
📊 Live berth availability display  

---

## 🎮 Example Run

```bash
=======================================
   Welcome to the Railway Reservation  
=======================================

=======================================

Menu:
1. Book Ticket
2. Cancel Ticket
3. Print All Tickets
4. Print Available Berths
5. Exit

=======================================

Enter your choice: 1
Enter Name: John Doe
Enter Age: 27
Enter Gender (M/F/O): M
Enter Berth Preference (L|M|U|SU): L

Ticket Booked Successfully!
Passenger{ name: John Doe, age: 27, gender: M, berthPreference: L, allotedBirth: L, Ticket ID: 98OHF7 }
Ticket ID: 98OHF7
Berth Alloted: L

```

---

## 🧠 Concepts Demonstrated

| Concept | Description |
|----------|--------------|
| **OOP Principles** | Encapsulation, modularity, and method abstraction |
| **Collections** | `List`, `HashMap` for ticket management |
| **Enums** | Configuring berth capacities dynamically |
| **Scanner & Input Handling** | Robust console input logic |
| **Algorithmic Thinking** | Seat allocation + reallocation workflow |

---

## ⚙️ How to Run

### 🧰 Prerequisites
- Java 17 or newer  
- Any terminal or IDE (VS Code, IntelliJ, Eclipse)

### 🏗️ Setup & Run
```bash
# 1️⃣ Clone the repository
git clone https://github.com/<your-username>/RailwayReservationSystem.git
cd RailwayReservationSystem

# 2️⃣ Compile
javac Main.java

# 3️⃣ Run
java Main.java
```

---

## 🧱 Future Enhancements

🔹 File-based or database persistence  
🔹 GUI using JavaFX or web interface  
🔹 Train & route management  
🔹 Ticket printing and PDF generation  
🔹 Seat visualization using ASCII or graphics  

---

## 👨‍💻 Author

**👤 Gilbert Johnson**  
 
🌐 [GitHub](https://github.com/GilbertJohnson) • [LinkedIn](www.linkedin.com/in/gilbert-johnson-7373b6255)  

> “The best way to predict the future is to code it.” 🚀  

---

## 📜 License
 
You’re free to use, modify, and share.  

---

## ❤️ Acknowledgements
- 🚀 Zoho — for inspiring this idea through an interview question!
- 💡 Java — for being evergreen  
- 🧠 You — for reading this far!

---

```
       ______
     _/[] []\_
___ /_==_==_==_\  🚂💨
|     RAILWAY     |
~~~  RESERVATION  ~~~
```

<p align="center">
⭐ <b>If you like this project, consider giving it a star!</b>  
<br>
<i>Every star keeps the train on track 🚄✨</i>
</p>
