package app.model.dto.attraction;

import app.model.dto.trip.TripDto;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDto
{
    private UUID id;
    private TripDto trip;
    private String title;
    private String imageUrl;
    private String description;
}

