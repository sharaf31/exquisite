# exquisite
Spring boot - MongoDB - Nginx

## Application to Manage Articles 

### Requirement
user should have docker installed on running machine.

### Frameworks
JDK 1.8 is used 
Spring boot 2
MongoDB 3.6 [reason to use mongoDB is it provides the text searches for the keyword]
Maven 3.5
Nginx 1.13 [the reason for using Nginx is to ]
Docker [reason to use docker is that above all the libraries need not to be present when running the software]

#### Running the Software
$ docker-compose up

#### Testing the software
Either user can test through browser or curl command 

###### Article save
curl -X "POST" "http://localhost/articles" -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "text": "irish coffee shop has miracle of curing anxiety local rumors",
  "publishDate": "23/04/2018",
  "author": [
    "hafeez"
  ],
  "header": "coffe miracle",
  "shortDescription": "man saved by drinking coffee"
}'

###### Article keyword
curl "http://localhost/articles/keyword/irish" 

###### Article findAll
curl "http://localhost/articles/"

###### Article get one
curl "http://localhost/articles/coffe%20miracle"

###### Article GET AUTHOR
curl "http://localhost/articles/author/hafeez"

###### Article GET on Given period
curl "http://localhost:7070/articles/period?begin=01%2F01%2F2018&end=20%2F04%2F2019"

###### Article delete
curl -X "DELETE" "http://localhost:7070/articles/coffe%20miracle"
