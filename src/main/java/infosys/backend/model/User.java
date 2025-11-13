package infosys.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import infosys.backend.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"services", "bookingsAsCustomer", "bookingsAsProvider",
                     "reviewsAsCustomer", "reviewsAsProvider", "documents",
                     "sentMessages", "receivedMessages", "sentNotifications",
                     "receivedNotifications", "reportsMade", "reportsReceived"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String location;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ---------- SERVICES ----------
    @OneToMany(mappedBy = "provider",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<ServiceProvider> services;

    // ---------- BOOKINGS ----------
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookingsAsCustomer;

    @OneToMany(mappedBy = "provider",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookingsAsProvider;

    // ---------- REVIEWS ----------
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Review> reviewsAsCustomer;

    @OneToMany(mappedBy = "provider",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Review> reviewsAsProvider;

    // ---------- DOCUMENTS ----------
    @OneToMany(mappedBy = "provider",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Document> documents;

    // ---------- CHAT NOTIFICATIONS ----------
    @OneToMany(mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ChatNotification> sentNotifications;

    @OneToMany(mappedBy = "receiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ChatNotification> receivedNotifications;

    // ---------- MESSAGES ----------
    @OneToMany(mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Message> receivedMessages;

    // ---------- REPORTS ----------
    @OneToMany(mappedBy = "reportedBy",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Report> reportsMade;

    // Your Report has "targetId" not user relationship — so skip reportsReceived
    // unless you map it properly later.

    private boolean isVerified = false;
}
