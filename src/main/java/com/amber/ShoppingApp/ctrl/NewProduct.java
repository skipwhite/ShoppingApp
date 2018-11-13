package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;


@WebServlet("/newProduct")
@MultipartConfig
public class NewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String dscr = request.getParameter("dscr");
		String category = request.getParameter("category");
		String price = request.getParameter("price");
		String inventory = request.getParameter("inventory");
		String launched = request.getParameter("launched");
		
//	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//	    InputStream fileContent = filePart.getInputStream();
//	    byte[] img = IOUtils.toByteArray(fileContent);

	    List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
	    List<byte[]> fileList = new ArrayList<>();
	    List<ProductImgBean> pibs = new ArrayList<>();
	    
	    for (Part filePart : fileParts) {
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        InputStream fileContent = filePart.getInputStream();
	        byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
//	        fileList.add(fileAsByteArray);
	        ProductImgBean pib = new ProductImgBean(fileAsByteArray);
	        pibs.add(pib);
	    }
		
		ProductBean pb = new ProductBean(name,dscr,category,Integer.parseInt(price),Integer.parseInt(inventory), new Boolean(launched));
		ProductService ps = new ProductServiceImpl();
		try {
			ps.createProduct(pb, pibs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		ProductImgService pis = new ProductImgServiceImpl();
//		try {
//			ps.insertSelective(pb);
//			pis.insert(pib);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	    
//		ProductImgService service = new ProductImgServiceImpl();
//		try {
//			ProductImgBean bean = new ProductImgBean();
//			bean.setProductId("00001");
//			bean.setImg(fileAsByteArray);
//			service.insert(bean);
//			
//			List<String> bls = service.selectByPrimaryKey("00001");
//			request.setAttribute("bls", bls);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
//	    System.out.println(fileName);
//		
//	    // ByteArray
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		int count = 0;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			// Check current item no.
//			String SELECT_MAX_ITEM = "select MAX(item) as item from AB_PRODUCT_IMG where product_id = ?";
//			ps = conn.prepareStatement(SELECT_MAX_ITEM);
//			ps.setString(1, "00001");
//			rs = ps.executeQuery();
//			
//			Integer maxItem = 1;
//			while (rs.next()) {
//				if(rs.getString(1) != null) {
//					maxItem = Integer.parseInt(rs.getString(1)) + 1;
//				}
//			}
//			
//			String INSERT = "insert into AB_PRODUCT_IMG values(?,?,?)";
//			ps = conn.prepareStatement(INSERT);
//			ps.setString(1, "00001");
//			ps.setString(2, maxItem.toString());
//			ps.setBytes(3, fileAsByteArray);
//			
//			count = ps.executeUpdate();
//			System.out.println("insert count : " + count);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}
	   
//		//ByteArray
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			String SELECT_BY_PK = "select * from AB_PRODUCT_IMG where product_id = ?";
//			ps = conn.prepareStatement(SELECT_BY_PK);
//			ps.setString(1, "00001");
//			rs = ps.executeQuery();
//			List<String> imgList = new ArrayList<>();
//			
//			while (rs.next()) {
//				byte[] img = rs.getBytes(3);
//				String base64 = Base64.getEncoder().encodeToString(img);
//				imgList.add(base64);
//			}
//				request.setAttribute("imgList", imgList);
//
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}	    

	    
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/newProduct.jsp");
		rd.forward(request, response);
	}

}
