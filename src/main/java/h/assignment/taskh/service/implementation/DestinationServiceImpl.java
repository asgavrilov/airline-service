package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.repo.DestinationRepository;
import h.assignment.taskh.service.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@Slf4j
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    @Override
    public DestinationDto create(DestinationDto entity) {
        Destination destination = Destination.builder()
                .airportId(entity.getAirportId().toLowerCase(Locale.ROOT))
                .country(entity.getCountry())
                .cityName(entity.getCityName())
                .build();
        log.info(entity.getCityName());
        destinationRepository.save(destination);
        return new DestinationDto(destination.getAirportId(), destination.getCountry(), destination.getCityName());
    }

    @Override
    public DestinationDto read(String id) {
        if (!destinationRepository.existsById(id.toLowerCase(Locale.ROOT))) {
            log.error("There is no destination with id {} in DB", id);
            throw new ResourceNotFoundException(String.format("Destination wth id %s does not exist", id));
        }
        Destination destination = destinationRepository.findById(id.toLowerCase()).orElse(null);
        if (destination == null) {
            throw new ResourceNotFoundException("no destination is found");
        }
        return new DestinationDto(destination.getAirportId(), destination.getCountry(), destination.getCityName());
    }

    @Override
    public List<DestinationDto> getAll() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations
                .stream()
                .map(d -> new DestinationDto(
                        d.getAirportId(),
                        d.getCountry(),
                        d.getCityName()
                )).toList();
    }

    @Override
    public DestinationDto update(String id, DestinationDto newEntity) {
        DestinationDto old = read(id.toLowerCase(Locale.ROOT));
        Destination newDest = Destination
                .builder()
                .airportId(old.getAirportId().toLowerCase(Locale.ROOT))
                .country(newEntity.getCountry())
                .cityName(newEntity.getCityName())
                .build();
        destinationRepository.save(newDest);
        return old;
    }

    @Override
    public DestinationDto remove(String id) {
        DestinationDto oldDto = read(id.toLowerCase(Locale.ROOT));
        destinationRepository.deleteById(id.toLowerCase(Locale.ROOT));
        return oldDto;
    }
}
