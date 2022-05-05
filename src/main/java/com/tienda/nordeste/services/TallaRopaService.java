package com.tienda.nordeste.services;

import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.models.talla.Talla;
import com.tienda.nordeste.models.talla.TallaRopa;
import com.tienda.nordeste.repositories.TallaRopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class TallaRopaService extends BaseService<TallaRopa, String, TallaRopaRepository> {
    @Autowired
    private TallaRopaRepository tallaRopaRepository;

    public Optional<TallaRopa> findByRopaIdAndTalla(String idRopa, Talla talla) {
        return tallaRopaRepository.findByRopaIdAndTalla(idRopa, talla);
    }

    public TallaRopa crearTallaRopa(Ropa ropa, Talla talla, Integer stock) throws IOException {
        TallaRopa tallaRopa = new TallaRopa();
        tallaRopa.setRopa(ropa);
        tallaRopa.setTalla(talla);
        tallaRopa.setStock(stock);
        return tallaRopa;
    }
}
