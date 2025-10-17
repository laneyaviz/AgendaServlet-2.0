package controles;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import modelos.Compromisso;
import modelos.CompromissoDAO;
 
@WebServlet(urlPatterns = {"/CompromissoServlet", "/agenda"})
public class CompromissoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Instanciando o DAO (Responsável pelo Banco de Dados)
    private CompromissoDAO dao = new CompromissoDAO();
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                // Lógica de exclusão (usando ID)
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deletarCompromisso(id); // Assumindo o método no DAO
            } else if (action.equals("edit")) {
                // Lógica de pré-carregamento de dados para edição
                // ...
            }
        }
        // Função que lista todos e encaminha para a JSP de agenda
        ListarCompromissos(request, response);
    }
 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Compromisso compromisso = new Compromisso();
        compromisso.setDescricao(request.getParameter("descricao"));
        compromisso.setLocal(request.getParameter("local"));
        // Conversão da String de data/hora do formulário para o tipo Date
        String dataHoraStr = request.getParameter("dataHora");
        try {
            // O formato do input type="datetime-local" é yyyy-MM-ddThh:mm
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dataHora = sdf.parse(dataHoraStr);
            compromisso.setHora(dataHora);
        } catch (ParseException e) {
            e.printStackTrace();
            // Tratar erro de conversão de data
        }
        // Se tivesse um ID, faria um método 'atualizarCompromisso'
        dao.salvarCompromisso(compromisso); // Assumindo o método no DAO
 
        // Redireciona para evitar reenvio do formulário
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