# MiniBank — OOP Practicals 1–14

A single integrated Java 17 console banking project covering the MiniBank requirements across Practicals 1–14.

## Requirements
- JDK 17 or newer
- Maven 3.8+ (recommended)

## Run in VS Code / terminal
From the `oop-minibank` project folder:

```bash
mvn clean package
java -jar target/oop-minibank-1.0.0.jar
```

If Maven is not installed, compile all sources with JDK 17+:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out MiniBank
```

On Windows Command Prompt, use Maven commands above, or compile from PowerShell with:

```powershell
$files = Get-ChildItem -Recurse src\main\java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out MiniBank
```

## Menu
1. Open account (Savings, Current, Fixed Deposit)
2. Deposit
3. Withdraw
4. Transfer
5. Account statement
6. List accounts
7. End-of-day report
8. Save and exit
9. Practical concept demonstrations (validators, parser, lambda, reflection and try-with-resources)

## Data files
- `data/accounts.dat` — serialized account state, created on exit
- `logs/transactions.log` — append-only transaction log
- `data/end-of-day-report.txt` — generated report

## Tests
Run `mvn test` to execute the JUnit tests.

## Practical coverage
- P1: main class, BankInfo record, menu enum, Scanner, loop and switch expression
- P2–P3: Customer, Account hierarchy foundation, constructors, encapsulation, toString, equals/hashCode, nested Address, clone and instanceof-compatible type checks
- P4: regex Validator, TransactionType, Command record/parser and StringBuilder statement
- P5: abstract Account and Savings/Current/FixedDeposit polymorphism
- P6: interfaces, default method, functional interface, lambda-ready rule, marker interface and Maven runnable JAR
- P7: custom runtime annotations and reflection validation
- P8: checked exception hierarchy, transfer handling and try-with-resources concept (see note below)
- P9–P10: synchronized account operations, Runnable workers, ExecutorService and wait/notify producer-consumer buffer
- P11: Serializable state, object persistence, append-only log and NIO report generation
- P12: HashMap account store, ArrayList history, Comparator sorting, Comparable and ConcurrentHashMap
- P13: generic Repository using @Id reflection, @SafeVarargs and bounded generic average
- P14: BankService, real menu operations, persistence on startup/exit and JUnit tests

## Notes for lab demonstration
The menu is the integrated final version, so it includes extra options beyond the original Practical 1 menu. `TransactionBuffer` demonstrates producer-consumer coordination; `AccountWorker` demonstrates Runnable threads; menu option 9 demonstrates validators, command parsing, anonymous-class/lambda rules, `instanceof`, reflection annotations and try-with-resources. The code prioritizes one coherent final application rather than reproducing intentionally broken race-condition or deadlock demonstrations in the production transaction path.
