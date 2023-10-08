
package serv.serv;
//importing all libraries
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author george
 */
@WebServlet(name = "test", urlPatterns = {"/test"}) //creating url pattern for this page
public class test extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //main class
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating standard html tags
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");          
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Loan Payment Calculator</h1>"); //heading for loan calculator
            out.println("<form action='test' method='post'>");
            out.println("<label for='loanAmount'>Loan Amount ($):</label>");
            out.println("<input type='number' id='loanAmount' name='loanAmount' required><br><br>"); //creating label and textfield for loan amount 
            out.println("<label for='interestRate'>Annual Interest rate:</label>");
            out.println("<input type='number' id='interestRate' name='interestRate' step='0.01' required><br><br>"); //creating label and textfield for interest rate
            out.println("<label for='numYears'>Number of years:</label>");
            out.println("<input type='number' id='numYears' name='numYears' required><br><br>"); //creating label and textfield for number of years
            out.println("<input type='submit' value='Calculate'>");//button to submit and add the calculations
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            //getting values from user input
            double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
            int numYears = Integer.parseInt(request.getParameter("numYears"));
            double interestRate = Double.parseDouble(request.getParameter("interestRate"));

            // displaying amount, rate, years, monthly payment, and total payment
            double monthlyInterestRate = (interestRate / 100) / 12;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numYears * 12)));
            double totalPayment = monthlyPayment * numYears * 12;
            out.println("<p>Loan Amount: $" + loanAmount + "</p>");
            out.println("<p>Interest Rate: " + interestRate + "%</p>");
            out.println("<p>Time (In years): " + numYears + "</p>");
            out.println("<p>Monthly Payment: $" + monthlyPayment + "</p>");
             out.println("<p>Total Payment: $" + totalPayment + "</p>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
