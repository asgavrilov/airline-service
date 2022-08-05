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

import java.util.List;

@Service
@Slf4j
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DestinationDto create(DestinationDto entity) {
        Destination destination = Destination.builder()
                .cityName(entity.getCityName())
                .build();
        log.info(entity.getCityName());
        destinationRepository.save(destination);
        return new DestinationDto(destination.getId(), destination.getCityName());
    }

    @Override
    public DestinationDto read(Integer id) {
        if (!destinationRepository.existsById(id)) {
            log.error("There is no destination with id {} in DB", id);
            throw new ResourceNotFoundException(String.format(
                    "Destination wth id %d does not exist", id
            ));
        }
        Destination destination = destinationRepository.getDestination(id);
        return new DestinationDto(destination.getId(), destination.getCityName());
    }

    @Override
    public List<DestinationDto> getAll() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations
                .stream()
                .map(d -> new DestinationDto(
                        d.getId(),
                        d.getCityName()
                )).toList();
    }

    @Override
    public DestinationDto update(Integer id, DestinationDto newEntity) {
        DestinationDto old = read(id);
        Destination newDest = Destination
                .builder()
                .id(old.getId())
                .cityName(newEntity.getCityName())
                .build();
        destinationRepository.save(newDest);
        return old;
    }

    @Override
    public DestinationDto remove(Integer id) {
        DestinationDto oldDto = read(id);
        destinationRepository.deleteById(id);
        return oldDto;
    }

    private Destination convertToEntity(DestinationDto entity) {
        return modelMapper.map(entity, Destination.class);
    }

}
