Here's the content converted into Markdown format:

```markdown
# Understanding @OneToOne Mapping in Spring Boot with JPA

Understanding `@OneToOne` mapping in Spring Boot with JPA requires a clear grasp of how relationships between entities are mapped in a database. Let’s break this down step by step.

## @OneToOne Mapping in Spring Boot

The `@OneToOne` annotation in JPA is used to map a one-to-one relationship between two entities. In a relational database, this means that one row in Table A is associated with exactly one row in Table B and vice versa.

### Basic Structure

#### Entity A

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    // Getters and setters
}
```

#### Entity B

```java
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;

    @OneToOne
    @JoinColumn(name = "user_id") // Foreign key column in the Address table
    private User user;

    // Getters and setters
}
```

### Explanation:
1. `@OneToOne`: Defines a one-to-one relationship.
2. `mappedBy`: Used on the non-owning side of the relationship to indicate that this side is mapped by the other entity.
3. `@JoinColumn`: Used on the owning side to specify the foreign key column.

### Uni-directional vs Bi-directional

#### Uni-directional:
- Only one entity is aware of the relationship.

Example:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id") // Foreign key column in the User table
    private Address address;

    // Getters and setters
}
```

In this case, only `User` knows about `Address`.

#### Bi-directional:
- Both entities are aware of the relationship.

Example:
The example provided earlier demonstrates a bi-directional relationship.

### Database Structure

For the bi-directional example:

- **User Table:**

| id | name |
|----|------|
| 1  | John |

- **Address Table:**

| id | city | state | user_id |
|----|------|-------|---------|
| 1  | NY   | NY    | 1       |

### Cascade Types

#### CascadeType.ALL:
- Propagates all operations (persist, merge, remove, refresh, detach) from the parent entity to the child entity.

#### When to Use Cascade:
- Use `CascadeType.ALL` if you want changes in one entity to reflect in the related entity.

Example with CascadeType:

```java
User user = new User();
user.setName("John");

Address address = new Address();
address.setCity("NY");
address.setState("NY");

user.setAddress(address);
address.setUser(user);

userRepository.save(user); // Saves both User and Address
```

### Fetch Types

#### Eager (Default for @OneToOne):
- Fetches the related entity immediately.

#### Lazy:
- Fetches the related entity only when accessed.

Example of Lazy Fetching:

```java
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "address_id")
private Address address;
```

#### Caveat with Lazy Loading:
- Lazy loading can throw `LazyInitializationException` if the session is closed before accessing the entity.

### Edge Cases

1. **Circular Dependency**  
   If both entities refer to each other, make sure to use `mappedBy` on the non-owning side to avoid infinite loops during serialization.

2. **Orphan Removal**  
   If you remove a `User`, do you want the `Address` to be deleted automatically? Use `orphanRemoval = true` with `CascadeType.ALL`.

```java
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "address_id")
private Address address;
```

3. **Shared Primary Key**  
   If you want the `Address` table to share the same primary key as the `User` table:

Example:

```java
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String city;
    private String state;
}
```

Here, the `Address` entity’s `id` column is also the foreign key to the `User` table.

### Common Mistakes

1. **Missing mappedBy**
    - Without `mappedBy`, Hibernate may create an extra table for managing the relationship.

2. **Not Handling Lazy Initialization**
    - Accessing a lazily-loaded property outside the transaction scope will throw an exception.

3. **Not Using orphanRemoval**
    - If you remove the parent entity without `orphanRemoval`, the child entity may remain in the database, causing orphan records.

### Complete Example with Repository and Service

#### Repository:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
```

#### Service:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserWithAddress() {
        User user = new User();
        user.setName("John");

        Address address = new Address();
        address.setCity("NY");
        address.setState("NY");

        user.setAddress(address);
        address.setUser(user);

        userRepository.save(user);
    }
}
```

### Testing

#### Main Application:

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

#### Runner:

```java
@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.saveUserWithAddress();
    }
}
```
