package app.model.dto.trip;

import app.model.dto.attraction.AttractionDto;
import app.model.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripDto
{
    private UUID id;
    private String destination;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserDto creator;
    private List<AttractionDto> attractions = new ArrayList<>();
}


