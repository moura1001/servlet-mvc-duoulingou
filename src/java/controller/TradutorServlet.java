package controller;

import model.Tradutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/traduzir")
public class TradutorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String palavra = request.getParameter("palavra");
        try {
            String dataPath = getServletContext().getRealPath("/WEB-INF/dados.txt");
            String traducao = Tradutor.obterInstancia(dataPath).traduzir(palavra);
            request.setAttribute("traducao", traducao);
        } catch (RuntimeException e) {
            request.setAttribute("erro", e.getMessage());
        }

        request.getRequestDispatcher("resposta.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
