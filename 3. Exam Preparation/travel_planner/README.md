# Travel Planner

## Overview

Travel Planner is a Spring Boot web application that allows users to organize, publish and join trips around the world. The platform provides an intuitive user interface for creating travel experiences, discovering destinations and managing attractions associated with each trip.

The project is developed for the Spring Fundamentals Exam and demonstrates the use of Spring MVC, Thymeleaf, Spring Data JPA, validation, authentication and role-based authorization.

---

## Technology Stack

* Java 17
* Spring Boot 3.4.0
* Spring MVC
* Spring Data JPA
* Spring Security
* Thymeleaf
* MySQL
* Maven
* HTML5
* CSS3

---

## Domain Entities

### User

Represents an application user.

Properties:

* UUID id
* username
* email
* password
* role

### Trip

Represents a travel event.

Properties:

* UUID id
* destination
* description
* startDate
* endDate
* creator

### Booking

Represents a reservation made by a traveler.

Properties:

* UUID id
* trip
* traveler
* bookingDate

### Attraction

Represents a tourist attraction associated with a trip.

Properties:

* UUID id
* trip
* title
* imageUrl
* description

---

## Functionalities

### Create Trip

Authenticated users can create new trips.

### Edit Trip

Trip creators can update existing trips.

### Delete Trip

Trip creators can remove trips.

### Join Trip

Users can join available trips.

### Cancel Booking

Users can cancel their participation.

### Manage Attractions

Trip creators can add, edit and remove attractions.

---

## Security

Guests can:

* Register
* Login
* Access public pages

Authenticated users can:

* Browse trips
* Join trips
* Manage their bookings

Trip creators can:

* Create trips
* Edit trips
* Delete trips
* Manage attractions

Passwords are stored using BCrypt hashing.

---

## Validation

All forms include server-side validation.

Examples:

* Required fields
* Minimum and maximum length
* Email validation
* Date validation
* Business rule validation

Validation messages are displayed directly next to invalid fields.

---

## Pages

* Home
* Login
* Register
* Trip Catalog
* Create Trip
* Edit Trip
* Trip Details
* My Trips
* Manage Attractions

---

## Relationships

Trip -> User (ManyToOne)

Booking -> User (ManyToOne)

Booking -> Trip (ManyToOne)

Attraction -> Trip (ManyToOne)

---

## Future Improvements

* Destination search
* Trip categories
* Image uploads
* Ratings and reviews
* Google Maps integration
* Email notifications
