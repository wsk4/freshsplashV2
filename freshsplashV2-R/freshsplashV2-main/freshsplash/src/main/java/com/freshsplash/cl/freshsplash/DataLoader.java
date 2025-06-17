package com.freshsplash.cl.freshsplash;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.freshsplash.cl.freshsplash.model.Bano;
import com.freshsplash.cl.freshsplash.model.Calificacion;
import com.freshsplash.cl.freshsplash.model.DiasAbierto;
import com.freshsplash.cl.freshsplash.model.Etiqueta;
import com.freshsplash.cl.freshsplash.model.Horario;
import com.freshsplash.cl.freshsplash.model.Imagen;
import com.freshsplash.cl.freshsplash.model.TipoSitio;
import com.freshsplash.cl.freshsplash.model.Ubicacion;
import com.freshsplash.cl.freshsplash.repository.BanoRepository;
import com.freshsplash.cl.freshsplash.repository.CalificacionRepository;
import com.freshsplash.cl.freshsplash.repository.DiasAbiertoRepository;
import com.freshsplash.cl.freshsplash.repository.EtiquetaRepository;
import com.freshsplash.cl.freshsplash.repository.HorarioRepository;
import com.freshsplash.cl.freshsplash.repository.ImagenRepository;
import com.freshsplash.cl.freshsplash.repository.TipoSitioRepository;
import com.freshsplash.cl.freshsplash.repository.UbicacionRepository;

import net.datafaker.Faker;

@Profile({"dev"})
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BanoRepository banoRepository;
    @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private EtiquetaRepository etiquetaRepository;
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private TipoSitioRepository tipoSitioRepository;
    @Autowired
    private DiasAbiertoRepository diasAbiertoRepository;

    public DataLoader() {
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        int i;
        for (i = 0; i < 10; ++i) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(i + 1);
            ubicacion.setPais(faker.address().country());
            ubicacion.setRegion(faker.address().state());
            ubicacion.setCiudad(faker.address().city());
            ubicacion.setComuna(faker.address().cityName());
            ubicacion.setDireccion(faker.address().streetName());
            this.ubicacionRepository.save(ubicacion);
        }
        String[] aspectosDelBano = {
            "limpieza", "estado del inodoro", "disponibilidad de papel", "olor", "privacidad", "accesibilidad", "espacio", "iluminación"
        };

        String[] adjetivos = {
            "excelente", "aceptable", "muy buena", "deficiente", "decepcionante", "regular", "agradable"
        };
        for (i = 0; i < 10; ++i) {
            String aspecto = faker.options().option(aspectosDelBano);
            String adjetivo = faker.options().option(adjetivos);
            String fraseExtra = faker.lorem().sentence();

            String comentario = "La " + aspecto + " del baño fue " + adjetivo + ". " + fraseExtra;

            Calificacion calificacion = new Calificacion();
            calificacion.setId(i + 1);
            calificacion.setComentario(comentario);
            calificacion.setPuntuacion(faker.number().numberBetween(0, 6));
            this.calificacionRepository.save(calificacion);
        }

        for (i = 0; i < 10; ++i) {
            String[] urls = {
                "https://example.com/img1.jpg",
                "https://example.com/img2.jpg",
                "https://example.com/img3.jpg",
                "https://example.com/img4.jpg",
                "https://example.com/img5.jpg",
                "https://example.com/img6.jpg",
                "https://example.com/img7.jpg",
                "https://example.com/img8.jpg",
                "https://example.com/img9.jpg",
                "https://example.com/img10.jpg"
            };

            Imagen imagen = new Imagen();
            imagen.setId(i + 1);
            imagen.setRuta(faker.options().option(urls));
            this.imagenRepository.save(imagen);
        }

        String[] nombresTipoSitio = {"Baño de Tienda", "Baño publico", "Baño de bencinera", "Baño de restaurante"};
        for (i = 0; i < 4; ++i) {
            String nombreTipoSitio = faker.options().option(nombresTipoSitio);
            TipoSitio tipoSitio = new TipoSitio();
            tipoSitio.setId(i + 1);
            tipoSitio.setNombreSitio(nombreTipoSitio);
            this.tipoSitioRepository.save(tipoSitio);
        }

        List<TipoSitio> tipoSitio = tipoSitioRepository.findAll();

        for (i = 0; i < 10; ++i) {
            Etiqueta etiqueta = new Etiqueta();
            etiqueta.setId(i + 1);
            etiqueta.setAccesoDiscapacitado(random.nextBoolean());
            etiqueta.setGratuito(random.nextBoolean());
            if (etiqueta.isGratuito()) {
                etiqueta.setPrecio(0);
            } else {
                etiqueta.setPrecio(faker.number().numberBetween(200, 1000));
            }
            etiqueta.setTipoSitio(tipoSitio.get(random.nextInt(tipoSitio.size())));
            this.etiquetaRepository.save(etiqueta);
        }

        String[] diasAbiertos = {"Lunes a Viernes", "Luner a Sabado", "Todos los dias"};
        for (i = 0; i < 3; i++) {
            String diaAbiertos = faker.options().option(diasAbiertos);
            DiasAbierto diasAbierto = new DiasAbierto();
            diasAbierto.setId(i + 1);
            diasAbierto.setDiaSemana(diaAbiertos);
            this.diasAbiertoRepository.save(diasAbierto);
        }

        List<DiasAbierto> diasAbierto = diasAbiertoRepository.findAll();

        for (i = 0; i < 5; ++i) {
            Horario horario = new Horario();
            horario.setId(i + 1);
            horario.setHoraApertura(LocalTime.of(faker.number().numberBetween(8, 12), faker.number().numberBetween(0, 60)));
            horario.setHoraCierre(LocalTime.of(faker.number().numberBetween(18, 20), faker.number().numberBetween(0, 60)));
            horario.setDiasAbierto(diasAbierto.get(random.nextInt(diasAbierto.size())));
            this.horarioRepository.save(horario);
        }

        List<Calificacion> calificacion = calificacionRepository.findAll();
        List<Etiqueta> etiqueta = etiquetaRepository.findAll();
        List<Imagen> imagen = imagenRepository.findAll();
        List<Horario> horario = horarioRepository.findAll();
        List<Ubicacion> ubicacion = ubicacionRepository.findAll();

        for (i = 0; i < 15; ++i) {
            Bano bano = new Bano();
            bano.setId(i + 1);
            bano.setCalificacion(calificacion.get(random.nextInt(calificacion.size())));
            bano.setEtiqueta(etiqueta.get(random.nextInt(etiqueta.size())));
            bano.setImagen(imagen.get(random.nextInt(imagen.size())));
            bano.setHorario(horario.get(random.nextInt(horario.size())));
            bano.setUbicacion(ubicacion.get(random.nextInt(ubicacion.size())));
            this.banoRepository.save(bano);
        }

    }
}
