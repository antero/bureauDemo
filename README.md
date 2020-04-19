# Java challenge

This project implements two APIs for translator data management and document search:

```
                           <<App1>>
                           +--------------------+
                           |                    |
                           |   Translator CRUD  |
                           |                    |
                           +--------------------+
                                   + ^
                                   | |
                                   | |
                                   | |
                                   | |
                                   | |
                                   | |
                                   | |
                                   v +
                      <<App2>>
                      +------------------------------+
                      |                              |
                      |                              |
                      |    Document Search Service   |
                      |                              |
                      |                              |
                      +------------------------------+

```

## How to run

A `docker-compose.yml` file is provided in the root directory for running both applications at the same time.
The compose file defines five services:

* `translator_api` - Builds and runs TranslatorCRUD gradle project on `localhost:8080`;
* `document_searcher` - Builds and runs Document Search gradle project on `localhost:8081`;
* `db_translator` - PostgreSQL database for `translator_api` running on `localhost:54320`;
* `db_document` - PostgreSQL database for `document_searcher` running on `localhost:54321`; and
* `pgadmin4` - Application for managing PostgreSQL databases running on `localhost:16543`;

Simply execute bash command `docker-compose up -d` to start the services and use and application like [Postman](https://www.postman.com/ "Postman website") to make the requests.

## Translator CRUD

This app runs on port `8080` and exposes the following endpoints:

* `GET /translators` - Returns a list of all translators registered in the database;
* `POST /translators` - Creates a new translator;
* `GET /translators/{id}` - Retrieves a translator with id equals `{id}`;
* `PUT /translators/{id}` - Updates a translator with id equals `{id}`;
* `DELETE /translators/{id}` - Deletes a translator with id equals `{id}`;
* `POST /translators/{id}/addSkills` - Adds translation skills do translator with id equals `{id}`;
* `GET /documents` - Returns a list of all documents registered in Document Searcher app (DS) database; and
* `GET /documents/search?query={text}` - Returns a list of documents in DS database that contain `{text}` in their title or body.

The `/documents` endpoints make request calls to Document Searcher app endpoinds using Feign.


### `POST /translators`

This endpoint registers a new translator in the database and expects a request body in the following format:

```json
{
  "name": "Fulano Silva",
  "email": "fulano@mail.com",
  "translationSkills": [
    {
      "sourceLanguage": "pt_br",
      "targetLanguage": "en_us"
    }
  ]
}
```

### `PUT /translators/{id}`

Updates translator with `{id}` and expects a request body in the following format:

```json
{
  "name": "New Fulano Name",
    "email": "new_fulano@mail.com"
}
```

### `POST /translators/{id}/addSkills`

Adds translation skills to translator with `{id}` and expects a request body in the following format:

```json
[
  {
    "sourceLanguage": "pt_br",
    "targetLanguage": "en_us"
  },
  {
    "sourceLanguage": "en_us",
    "targetLanguage": "pt_br"
  }
]
```

## Document Searcher

This app runs on port `8081` and exposes the following endpoints:

* `GET /documents` - Returns a list of all documents registered in the database;
* `POST /documents` - Provides bulk registration of new documents in the database; and
* `GET /documents/search?query={text}` - Returns a list of registered documents that contain `{text}` in their title or body.

### `POST /documents`

Convenience endpoint to save a list of documents in the database. It expects a request body in the following format:

```json
[
  {
    "translatorId": 1,
    "title": "Bureau Platform",
    "body": "Getting content to your international markets doesn’t need to be disjointed and overwhelming. Bureau Works puts you back in control by aligning your people, content and processes - all in one platform."
  },
  {
    "translatorId": 1,
    "title": "Translation Memory",
    "body": "Imagine every localization project flowing through a single platform, no matter how it was sent. What if you had every language vendor, internal reviewer and project manager working in concert, using the same terminology and leveraging the same translation memories."
  },
  {
    "translatorId": 2,
    "title": "Collaboration",
    "body": "Transparency drives accountability. Thread-based communication is woven into the system and tied directly to decisions. That means no more guessing how a certain translation choices came to be."
  },
  {
    "translatorId": 3,
    "title": "Automation",
    "body": "Bureau Works uses customized tags to automate the assignment of projects to the right talent. Automation algorithms leverage tagging along with deep data intelligence to maximize production efficiency."
  }
]
```

## Observations

The PostgreSQL containers are not required for running the test cases of neither app. In-memory databases (HSQLDB) are set for testing purposes.

A PgAdmin4 service is provided in the docker-compose file and runs on `localhost:16543` with credentials `user=admin, password=admin`. To add a database go to `Servers -> Dashboard -> Add New Server`. On the `Connection` tab, input the following values:

* `Host name/address` - {container_service} (db_translator or db_document);
* `Port` - 5432;
* `Username` - postgres; and
* `Password` - Postgres2020!
