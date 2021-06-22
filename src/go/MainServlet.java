package go;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dao.Member;
import com.ssafy.happyhouse.model.service.UserService;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if (action == null) {
				checkCookie(request, response);
			} else if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("logout")) {
				logout(request, response);
			} else if (action.endsWith("joinform")) {
				joinform(request, response);
			} else if (action.endsWith("join")) {
				join(request, response);
			} else if (action.equals("list")) {
				list(request, response);
			} else if (action.equals("delete")) {
				delete(request, response);
			} else if (action.equals("deleteform")) {
				deleteform(request, response);
			}else if (action.equals("add")) {
				add(request, response);
			}else if (action.equals("addform")) {
				addform(request, response);
			}
		} catch (Exception e) {
			// 로깅은 매우 중요하다.
			e.printStackTrace();
			// 예외 총집합 --> 서버 오류는 여기서 컨트롤해주자.
//			request.setAttribute("error", e.getMessage());
//			RequestDispatcher disp = request.getRequestDispatcher("/error/500.jsp");
//			disp.forward(request, response);
			// 예외를 던져버리자. 누구에게?????????
			throw new ServletException(e);
		}

	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/session/add.jsp");

	}
	private void addform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
//		1. parameter 분석 --> Member(DTO) 
		String memName = request.getParameter("memName");
		String memEmail = request.getParameter("memEmail");
		String memPwd = request.getParameter("memPwd");
		Member member = new Member(memName, memEmail, memPwd);
//		2. member 를 Service에게 전달
		int result = UserService.getUserService().join(member);
//		3. login 유도
		if (result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher("/admin.jsp");
			dis.forward(request, response);
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("/admin.jsp");
			dis.forward(request, response);
		}

	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/session/deleteform.jsp");
	}

	private void deleteform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memName = request.getParameter("memName");
		int result = UserService.getUserService().delete(memName);
		if (result == 1) {
			request.setAttribute("msg", "삭제되었습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("/admin.jsp");
			disp.forward(request, response);
		} else {
			RequestDispatcher disp = request.getRequestDispatcher(request.getContextPath() + "/session/delete.jsp");
			disp.forward(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		List<Member> member = UserService.getUserService().search();
		HttpSession session = request.getSession();
		session.setAttribute("member", member);

		response.sendRedirect(request.getContextPath() + "/session/list.jsp");
	}

	private void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
//		1. parameter 분석 --> Member(DTO) 
		String memName = request.getParameter("memName");
		String memEmail = request.getParameter("memEmail");
		String memPwd = request.getParameter("memPwd");
		Member member = new Member(memName, memEmail, memPwd);
		System.out.println(11);
//		2. member 를 Service에게 전달
		int result = UserService.getUserService().join(member);
//		3. login 유도
		if (result == 1) {
			request.setAttribute("msg", "가입 성공. 로그인 후 사용해주세요.");
			RequestDispatcher dis = request.getRequestDispatcher("/signup.jsp");
			dis.forward(request, response);
		} else {
			request.setAttribute("msg", "가입 실패. 다시 시도하세요");
			RequestDispatcher dis = request.getRequestDispatcher("/signup.jsp");
			dis.forward(request, response);
		}

	}

	private void joinform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/join.jsp");
		dis.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. cookie 삭제
		Cookie[] cookies = request.getCookies();
		for (Cookie id : cookies) {
			if (id.getName().equals("loginId")) {
				id.setMaxAge(0);
				id.setPath("/");
				response.addCookie(id);
			}
		}

		// 2. session 종료
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");

		// 3. index.jsp로 이동
		response.sendRedirect("index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// UserService를 현동해서 로그인 가능한지 확인
		String memName = request.getParameter("memName");
		String memPwd = request.getParameter("memPwd");
		UserService service = UserService.getUserService();
		boolean success = service.login(memName, memPwd);
		// 성공 --> main.jsp, 세션 등록, 쿠키 저장
		if (success) {
			Cookie cookie = new Cookie("loginId", memName);
			cookie.setMaxAge(60 * 1); // 60초 단명하는 쿠키
			cookie.setPath("/");
			response.addCookie(cookie);

			HttpSession session = request.getSession();
			session.setAttribute("loginId", memName);

			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		// 실패 --> index.jsp
		else {
			request.setAttribute("loginmsg", "아이디/비밀번호를 확인하세요.");
			RequestDispatcher dis = request.getRequestDispatcher("/signup.jsp");
			dis.forward(request, response);
		}

	}

	private void checkCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Cookie에서 loginId라는 녀석이 있는지 살펴본다
		Cookie[] cookies = request.getCookies();
		Cookie loginId = null;
		if (cookies != null) {
			for (Cookie id : cookies) {
				if (id.getName().equals("loginId")) {
					loginId = id;
					break;
				}
			}
		}

		// 2. 있다면 main.jsp로, 없다면 index.jsp로 이동
		if (loginId == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		// 2-1 main으로 가기저에 session에 login Id를 담아두기
		else {
			HttpSession session = request.getSession();
			session.setAttribute("loginId", loginId.getValue());

			RequestDispatcher dis = request.getRequestDispatcher(request.getContextPath() + "session/main.jsp");
			dis.forward(request, response);
		}
	}

//	private void template(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//	}

}