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

java -version


Check Maven version:

mvn -v


## Installation

Clone the repo:

git clone https://github.com/github_username/repo_name.git


Change directory:

cd repo_name


Build the project:

mvn clean package


## Usage

Run the packaged JAR:

java -jar target/simplelogger-1.0-SNAPSHOT-shaded.jar


This will create a `data/entries.json` file in the same folder if it doesn't already exist.

## Tests

Run unit tests:

mvn test

## Roadmap

- Add more filter options
- Add sorting options
- Improve CLI UI
- Add more unit tests

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

Project Link: https://github.com/github_username/repo_name


## Acknowledgments

- Jackson documentation
- Maven documentation
- JUnit documentation