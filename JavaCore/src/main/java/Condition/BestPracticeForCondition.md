# Best Practices for Conditional Variable Assignment in Java (Java 8 - 21)

This guide outlines the best practices for setting values to variables based on conditions in modern Java (Java 8 through Java 21).

---

## 1. Use the Ternary Operator for Simple Conditions

```java
String status = isActive ? "ACTIVE" : "INACTIVE";
```

- ✔️ Concise
- ❌ Avoid for complex logic

---

## 2. Use `if-else` Blocks for Complex Logic

```java
String result;
if (value > 100) {
    result = "High";
} else if (value > 50) {
    result = "Medium";
} else {
    result = "Low";
}
```

- ✔️ Clear and extensible
- ✔️ Readable for multiple branches

---

## 3. Use `switch` or `switch` Expressions (Java 12+)

```java
String label = switch (status) {
    case "OPEN" -> "Open label";
    case "CLOSED" -> "Closed label";
    default -> "Unknown";
};
```

- ✔️ Modern, clean syntax
- ❌ Requires Java 12 or higher

---

## 4. Use Helper Methods to Encapsulate Logic

```java
String label = getStatusLabel(status);

private String getStatusLabel(String status) {
    if ("OPEN".equals(status)) return "Open label";
    if ("CLOSED".equals(status)) return "Closed label";
    return "Unknown";
}
```

- ✔️ Improves code organization
- ✔️ Enhances testability

---

## 5. Use `Optional` for Nullable Conditions (Java 8+)

```java
String displayName = Optional.ofNullable(user.getName())
    .filter(name -> !name.isBlank())
    .orElse("Guest");
```

- ✔️ Avoids explicit null checks
- ✔️ Expresses intent clearly

---

## 6. Use Guard Clauses to Avoid Nesting

```java
if (value == null) return;
if (!value.isValid()) return;

this.result = value.getResult();
```

- ✔️ Reduces nesting
- ✔️ Improves readability

---

## 7. Use Strategy Pattern or Map for Conditional Behavior

```java
Map<String, Function<String, String>> processorMap = Map.of(
    "PDF", this::processPDF,
    "DOC", this::processDOC
);

String result = processorMap.getOrDefault(type, this::processDefault).apply(input);
```

- ✔️ Scales better than long `if-else`
- ✔️ Easy to extend

---

## Anti-Patterns to Avoid

| Anti-Pattern                         | Reason                        |
|-------------------------------------|-------------------------------|
| Deeply nested `if-else`             | Hard to read                  |
| Complex ternary expressions         | Reduces clarity               |
| Repeated conditional code blocks    | Harder to maintain            |
| Side-effects inside conditionals    | Makes testing difficult       |

---

## Summary Table

| Scenario                            | Recommended Practice         |
|------------------------------------|------------------------------|
| Simple true/false condition        | Ternary operator             |
| Multi-branch logic                 | `if-else` or `switch`        |
| Value-based assignment             | `switch` expression (Java 12+)|
| Complex logic                      | Helper methods               |
| Null/optional checks               | `Optional`                   |
| Strategy-based assignment          | `Map<String, Function>`      |
| Avoiding nesting                   | Guard clauses                |