package app.model.entity.booking;

import java.time.LocalDate;
import java.util.UUID;

import app.model.entity.trip.Trip;
import app.model.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id")
    private User traveler;

    @Column(name = "booking_date")
    private LocalDate bookingDate;
}

