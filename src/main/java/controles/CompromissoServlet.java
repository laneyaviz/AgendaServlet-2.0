package controles;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Compromisso;
import modelos.CompromissoDAO;
 
@WebServlet(urlPatterns = {"/CompromissoServlet", "/Agenda"})
public class CompromissoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CompromissoDAO dao = new CompromissoDAO();
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deletarCompromisso(id); 
            } else if (action.equals("edit")) {
             // ...
            }
        }
        ListarCompromissos(request, response);
    }
 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        Compromisso compromisso = new Compromisso();
        compromisso.setDescricao(request.getParameter("descricao"));
        compromisso.setLocal(request.getParameter("local"));
        String dataHoraStr = request.getParameter("dataHora");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dataHora = sdf.parse(dataHoraStr);
            compromisso.setHora(dataHora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dao.salvarCompromisso(compromisso); 
 
        response.sendRedirect("CompromissoServlet");
    }
 
   
    private void ListarCompromissos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Compromisso> lista = dao.listarTodos(); // Assumindo o método no DAO
        // Passa a lista para o JSP
        request.setAttribute("compromissos", lista);
        // Encaminha a requisição para a página de visualização da agenda
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }
}