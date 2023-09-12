package br.lucasmsf.aded.character;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    private void checkIfExistsById(Long id) throws ResourceNotFoundException {
        if (!this.characterRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
    }

    public List<Character> findAll() {
        return this.characterRepository.findAll();
    }

    public Character find(Long id) throws ResourceNotFoundException {
        return this.characterRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Character create(Character character) {
        return this.characterRepository.save(character);
    }

    public Character update(Character character, Long id) {
        this.checkIfExistsById(id);
        character.setId(id);
        return this.characterRepository.save(character);
    }

    public void delete(Long id) {
        this.checkIfExistsById(id);
        this.characterRepository.deleteById(id);
    }
}
