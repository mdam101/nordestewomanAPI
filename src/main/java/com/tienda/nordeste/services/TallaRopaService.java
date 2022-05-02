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

    public Optional<TallaRopa> findByRopaAndTalla(String Ropa, String talla) {
        return tallaRopaRepository.findByRopaAndTalla(Ropa, talla);
    }

    public TallaRopa crearTallaRopa(Ropa idRopa, String talla, Integer stock) throws IOException {
        TallaRopa tallaRopa = new TallaRopa();
        tallaRopa.setRopa(idRopa);
        tallaRopa.setTalla(Talla.valueOf(talla));
        tallaRopa.setStock(stock);
        return tallaRopa;
    }
}
