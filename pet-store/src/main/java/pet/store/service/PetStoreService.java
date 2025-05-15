package pet.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PetStoreService {

    @Autowired
    private PetStoreDao petStoreDao;

    @Transactional
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
        copyPetStoreFields(petStore, petStoreData);
        PetStore savedPetStore = petStoreDao.save(petStore);
        return new PetStoreData(savedPetStore);
    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
            return new PetStore();
        } else {
            return findPetStoreById(petStoreId);
        }
    }

    private PetStore findPetStoreById(Long petStoreId) {
        Optional<PetStore> petStore = petStoreDao.findById(petStoreId);
        if (petStore.isPresent()) {
            return petStore.get();
        } else {
            throw new NoSuchElementException("Pet store with ID " + petStoreId + " not found.");
        }
    }
    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}