package app.service.trip;

import app.model.dto.trip.TripDto;
import app.model.entity.trip.Trip;
import app.model.entity.user.User;
import app.repository.trip.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripService
{
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository)
    {
        this.tripRepository = tripRepository;
    }

    public List<TripDto> getAllTrips()
    {
        return tripRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public TripDto getTripById(UUID id)
    {
        return tripRepository.findById(id).map(this::entityToDto).orElse(null);
    }

    public TripDto createTrip(TripDto tripDto, User creator)
    {
        Trip trip = new Trip();
        trip.setDestination(tripDto.getDestination());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setCreator(creator);

        Trip savedTrip = tripRepository.save(trip);
        return entityToDto(savedTrip);
    }

    public TripDto updateTrip(UUID id, TripDto tripDto)
    {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));

        trip.setDestination(tripDto.getDestination());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());

        Trip updatedTrip = tripRepository.save(trip);
        return entityToDto(updatedTrip);
    }

    public void deleteTrip(UUID id)
    {
        tripRepository.deleteById(id);
    }

    private TripDto entityToDto(Trip trip)
    {
        return TripDto.builder()
                .id(trip.getId())
                .destination(trip.getDestination())
                .description(trip.getDescription())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .creator(trip.getCreator() != null ? app.model.dto.user.UserDto.builder()
                        .id(trip.getCreator().getId())
                        .username(trip.getCreator().getUsername())
                        .email(trip.getCreator().getEmail())
                        .role(trip.getCreator().getRole())
                        .build() : null).build();
    }
}

