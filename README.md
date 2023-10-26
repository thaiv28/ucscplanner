# UCSC Graduation Planner (Work in Progress)
A CLI application created for UC Santa Cruz students to determine if their
course plan will satisfy graduation requirements. 
## Usage
Requires a CSV file with the course codes of proposed classes in the first
column.

Use maven to run the java application. Use the command "checksc" with the csv
file as the only parameter. In this example we use the 'test' subcommand.
```java
mvn spring-boot:run -Dspring-boot.run.arguments="checksc test example.csv"
```
This will return a statement confirming or denying that the proposed courses
will meet graduation requirements. 

### Subcommands
- **gens** - checks if course list satisfies all general education requirements
- **credits** - checks if course list has satisfies credit minimum and maximum requirements (180 - 225)

[Sample CSV File](./src/main/resources/example.csv)

## Future Improvements
- Run command using simply "checksc example.csv" by implementing GraalVM Native
Image
- Check for major requirements and prerequisites

## Technologies
**Languages:** Java, Python (for web scraping) 
<br />**Frameworks:** Spring Boot, Picocli, H2

