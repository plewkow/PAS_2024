# Resource Allocation REST API

## Project Description
The **Resource Allocation REST API** is an server-side application implementing a simple business model where users can exclusively allocate specific resources for a defined time period.

## Features
1. CRUD Operations for Resources:
  * Create, read, update, and delete resources.
  * Resource deletion is only possible if no ongoing or unfinished allocations are associated with it.

2. CRUD Operations for Users:
  * Create, read, and update users.
  * Users can be activated or deactivated to control access to the system.

3. Resource Allocation:
  * Allocate resources to active users based on availability (no existing unfinished allocations).
  * Allow specifying the start time for allocations, including future dates.
  * End allocations by setting an end time or delete unfinished allocations.

4. Search Functionality:
  * Search for all object types by their unique identifier.
  * Search for users by their text-based login:
  * Exact match (returns a single user).
  * Partial match (returns a list of users).

5. Allocation Management:
  * Retrieve separate lists of past and current allocations for a specific user or resource.

6. Validation and Data Integrity:
  * Validate input data to ensure syntactical correctness and business logic consistency.
  * Maintain uniqueness for keys and identifiers in the system.

## Technology Stack
* **Backend**: Spring Boot
* **Database**: MongoDB (Dockerized)
* **REST API**: Exposes endpoints for managing users, resources and allocations
* **Testing**: JUnit with integration tests using tools like RestAssured
* **Containerization**: Docker for running MongoDB

### REST API Endpoints

#### Users
* `GET /api/users` - Retrieve a list of all users, with an optional query parameter `role` to filter the results by a specific user role. If the role parameter is provided, only users with the specified role will be included in the response.
* `POST /api/users` - Create a new user.
* `GET /api/users/:id` - Retrieve a specific user.
* `PUT /api/users/:id` - Update a specific user
* `PUT /api/users/activate/:id` - Activate a specific user
* `PUT /api/users/deactivate/:id` - Deactivate a specific user

#### Items
* `POST /api/items` - Create a new item
* `GET /api/items/:id` - Retrieve a specific item.
* `GET /api/items/name/:itemName` - Retrieve a list of items by itemName.
* `GET /api/items/type/:itemType` - Retrieve a list of items by itemType.
* `GET /api/items/type/:basePrice` - Retrieve a list of items by basePrice.
* `PUT /api/items/:id` - Update a specific item.
* `DELETE /api/items/:id` - Delete a specific item.

#### Rents
* `POST /api/rents` - Create a new rent.
* `PUT /api/rents/return/:id` - End a specific rent.
* `GET /api/rents/:id` - Retrieve a specific rent.
* `GET /api/rents/active` - Retrieve a list of active rents.
* `GET /api/rents/client/:clientId` - Retrieve list of rents by client.
* `GET /api/rents/active/client/:clientId` - Retrieve list of active rents by client.

## License
This project is licensed under the MIT License.
