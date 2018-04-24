# exquisite
Spring boot - MongoDB - Nginx

## Application to Manage Articles 

### Requirement
user should have docker installed on running machine.

### Frameworks
JDK 1.8 is used
Spring boot 2
MongoDB 3.6
Maven 3.5
Nginx 1.13

### Running the Software
$ docker-compose up

### Testing the software
Either user can test through browser or curl command 

#### Article save
curl -X "POST" "http://localhost/articles" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "text": "irish coffee shop has miracle of curing anxiety local rumors",
  "publishDate": "23/04/2018",
  "author": [
    "hafeez"
  ],
  "header": "coffe miracle",
  "shortDescription": "man saved by drinking coffee"
}'
