# NimapTask Explaination
This repository contains the solution for the task given by Nimap Infotech

## Task
Below requirements need to be implemented in machine tests.


A) Use Spring boot

B) Use Rest controller

C) Need DB configuration (Use RDB instead of in-memory)

D) Use annotation based configuration (Not XML).

E) JPA & Hibernate

1) Category CRUD operation.

 Below are the APIs required to be developed :

 1  http://localhost:8080/api/categories?page=3    GET all the categories

2  http://localhost:8080/api/categories            POST - create a new category

3  http://localhost:8080/api/categories/{di}       GET category by Id

4  http://localhost:8080/api/categories/{di}       PUT - update category by id

5  http://localhost:8080/api/categories/{di}       DELETE - Delete category by id

 2) Product CRUD operation.

Below are the APIs required to be developed :

 1   http://localhost:8080/api/products?page=2     GET all the products

2    http://localhost:8080/api/products            POST - create a new product

3    http://localhost:8080/api/products/{di}       GET product by Id

4    http://localhost:8080/api/products/{di}       PUT - update product by id

5    http://localhost:8080/api/products/{di}       DELETE - Delete product by id

3) Relation between Category-Products should have one-to-many relation. (One category can have multiple products).

4) Machine test should have Server side pagination.

5) While fetching single product details the response should be populated with respective category details.


## Solution

## CategoryController

The `CategoryController` class is a REST controller responsible for handling HTTP requests related to `Category` entities.

### Endpoints

- **GET /api/categories**: Retrieves all categories with optional pagination.
- **POST /api/categories**: Creates a new category.
- **GET /api/categories/{id}**: Retrieves a category by its ID.
- **PUT /api/categories/{id}**: Updates the name of a category by its ID.
- **DELETE /api/categories/{id}**: Deletes a category by its ID.


## ProductController

The `ProductController` class is a REST controller responsible for handling HTTP requests related to `Product` entities.

### Endpoints

- **GET /api/products**: Retrieves all products with optional pagination.
- **POST /api/products**: Creates a new product.
- **GET /api/products/{id}**: Retrieves a product by its ID.
- **PUT /api/products/{id}**: Updates a product by its ID.
- **DELETE /api/products/{id}**: Deletes a product by its ID.

