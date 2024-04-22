package br.com.clinicamedica.servlet;

import br.com.clinicamedica.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-senha")
public class UpdateSenha extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String novaSenha = req.getParameter("confirmar-senha");
        // Recuperar o CPF da sessão
        String cpf = (String) req.getSession().getAttribute("cpf");

        if (new UsuarioDAO().redefinicaoSenha(cpf, novaSenha)) {
            req.getRequestDispatcher("login.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("esqueciSenha.html").forward(req, resp);
        }
    }
}
