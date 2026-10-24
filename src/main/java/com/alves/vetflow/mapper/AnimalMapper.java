package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Animal;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.AnimalRequest;
import com.alves.vetflow.dto.response.AnimalResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AnimalMapper {

    public Animal toEntity(AnimalRequest animalRequest, Usuario tutor) {
        Animal animal = new Animal();
        animal.setNome(animalRequest.nome());
        animal.setEspecie(animalRequest.especie());
        animal.setPeso(animalRequest.peso());
        animal.setTutor(tutor);
        animal.setDataNascimento(animalRequest.dataNascimento());
        animal.setRaca(animalRequest.raca());
        return animal;
    }

    public AnimalResponse toDTO(Animal animal) {
        return new AnimalResponse(
                animal.getId(), animal.getTutor().getUsername(), animal.getNome(), animal.getEspecie(), animal.getRaca(), animal.getDataNascimento(), animal.getPeso()
        );
    }

}
