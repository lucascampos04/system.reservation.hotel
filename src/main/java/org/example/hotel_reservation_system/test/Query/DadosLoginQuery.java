package org.example.hotel_reservation_system.test.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.hotel_reservation_system.model.DadosLogin.DadosLogin;

import java.util.List;

public class DadosLoginQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("My_PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DadosLogin> criteriaQuery = criteriaBuilder.createQuery(DadosLogin.class);
            Root<DadosLogin> root = criteriaQuery.from(DadosLogin.class);
            criteriaQuery.select(root);

            TypedQuery<DadosLogin> query = entityManager.createQuery(criteriaQuery);
            List<DadosLogin> dadosLogins = query.getResultList();

            // Configurando ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enableDefaultTyping(); // Para permitir a deserialização de tipos
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Para formatação bonita

            String jsonResult = null;
            try {
                jsonResult = objectMapper.writeValueAsString(dadosLogins);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("Dados de login em formato JSON:");
            System.out.println(jsonResult);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
