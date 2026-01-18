# SimpleLogger

SimpleLogger is a basic command-line program that lets you log Movies, Shows, Books, or Other media.  
It stores entries in a JSON file and supports create, read, update, and delete (CRUD) operations.  
This project was made to practice building a clean Java application with file-based persistence and basic unit testing.

## About The Project

SimpleLogger lets you:
- Add entries (type, name, date finished, rating, comments)
- List entries
- Search entries by name, type, or rating
- Edit entries
- Delete entries  

All data is stored in `data/entries.json`.

## Built With

- Java 17+
- Maven
- Jackson (JSON serialization)
- JUnit 5

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

You need Java 17+ and Maven installed.

Check Java version:

```bash
java -version
```

Check Maven version:

```bash
mvn -v
```

## Installation

Clone the repo:

```bash
git clone https://github.com/asmith215/simplelogger.git
```

Change directory:

```bash
cd simplelogger
```

Build the project:

```bash
mvn clean package
```

## Usage

Run the packaged JAR:

```bash
java -jar target/simplelogger-1.0-SNAPSHOT-shaded.jar
```

This will create a `data/entries.json` file in the same folder if it doesn't already exist.

## Tests

Run unit tests:

```bash
mvn test
```

## Contributing

Contributions are welcome. If you want to contribute:

1. Fork the project  
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)  
3. Commit your changes (`git commit -m "Add some feature"`)  
4. Push to the branch (`git push origin feature/AmazingFeature`)  
5. Open a Pull Request  


## License

Distributed under the MIT License. See `LICENSE.txt` for more information.


## Contact

Alexander Smith – smith.p.alexander@gmail.com

Project Link: https://github.com/asmith215/simplelogger


## Acknowledgments

- [Jackson documentation](https://github.com/FasterXML/jackson)
- [Maven documentation](https://maven.apache.org/)
- [JUnit documentation](https://junit.org/junit5/)