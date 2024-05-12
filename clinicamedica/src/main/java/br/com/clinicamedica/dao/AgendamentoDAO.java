package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AgendamentoDAO {

    public void cadastrarAgendamento(Agendamento agendamento) {

    }

    public void confirmarAgendamento(Agendamento agendamento) {

    }

    public void cancelarAgendamento(Agendamento agendamento) {

    }

    public void finalizarAgendamento(Agendamento agendamento) {

    }

    public List<Agendamento> buscarAgendamentos(Usuario usuario) {
        return Collections.emptyList();
    }

    public List<Agendamento> buscarConsultasDoDia() {
        List<Agendamento> consultasDoDia = new ArrayList<>();
        final String url = "jdbc:h2:~/test";
        final String usuario = "sa";
        final String senha = "sa";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT a.*, u.nome, h.horario, d.data " +
                    "FROM Agendamento a " +
                    "JOIN Paciente p ON a.id_paciente = p.id_paciente " +
                    "JOIN Usuario u ON p.id_usuario = u.id_usuario " +
                    "JOIN Disponibilidade d ON a.id_disponibilidade = d.id_disponibilidade " +
                    "JOIN Horarios h ON d.id_horarios = h.id_horarios " +
                    "WHERE DATE(d.data) = CURDATE() AND a.confirmada = TRUE";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("nome"));
                agendamento.setPaciente(paciente);

                Disponibilidade disponibilidade = new Disponibilidade();
                Horario horario = new Horario();
                horario.setHorarioSelecionado(rs.getString("horario"));
                disponibilidade.setHorario(horario);
                disponibilidade.setData(rs.getString("data"));
                agendamento.setDisponibilidade(disponibilidade);

                consultasDoDia.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultasDoDia;
    }
}
