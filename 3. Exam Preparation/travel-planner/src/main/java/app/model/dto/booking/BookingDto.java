package app.model.dto.booking;

import app.model.dto.trip.TripDto;
import app.model.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto
{
    private UUID id;
    private TripDto trip;
    private UserDto traveler;
    private LocalDate bookingDate;
}

