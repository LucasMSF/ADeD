package br.lucasmsf.aded.character;

import br.lucasmsf.aded.character.dto.CharacterRequest;
import br.lucasmsf.aded.character.dto.CharacterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/characters")
@Tag(name = "Characters", description = "Manage the characters")
public class CharacterController {
    private final CharacterService characterService;

    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Get all characters")
    public ResponseEntity<List<CharacterResponse>> findAll() {
        var characterResponseList = this.characterService.findAll()
                .stream()
                .map(character -> this.modelMapper.map(character, CharacterResponse.class))
                .toList();

        return new ResponseEntity<>(characterResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get character by ID")
    public ResponseEntity<CharacterResponse> findById(@PathVariable Long id) {
        var characterResponse = modelMapper.map(
                this.characterService.find(id),
                CharacterResponse.class
        );

        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create new charatcer")
    public ResponseEntity<CharacterResponse> create(
            @RequestBody @Valid CharacterRequest characterRequest
    ) {
        var character = this.characterService.create(
                this.modelMapper.map(
                        characterRequest,
                        Character.class
                )
        );

        var characterResponse = this.modelMapper.map(
                character,
                CharacterResponse.class
        );

        return new ResponseEntity<>(characterResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update charatcer by ID")
    public ResponseEntity<CharacterResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid CharacterRequest characterRequest
    ) {
        var character = this.characterService.update(
                this.modelMapper.map(
                        characterRequest,
                        Character.class
                ),
                id
        );

        var characterResponse = this.modelMapper.map(
                character,
                CharacterResponse.class
        );

        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete character by ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
