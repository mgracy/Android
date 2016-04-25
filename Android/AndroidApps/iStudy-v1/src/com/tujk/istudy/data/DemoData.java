/**
 * 
 */
package com.tujk.istudy.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tujk.istudy.utils.Constants;
import com.tujk.istudy.vo.Book;
import com.tujk.istudy.vo.BookContent;
import com.tujk.istudy.vo.DownloadBook;
import com.tujk.istudy.vo.Order;
import com.tujk.istudy.vo.Plan;
import com.tujk.istudy.vo.Purchase;
import com.tujk.istudy.vo.User;

/**
 * title  : DemoData.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class DemoData {
	
	public static List<Book> getMainListBooks(){
		List<Book> result = new ArrayList<Book>();
		for(int i=1;i<=15;i++){
			Book book =  new Book();
			book.setId("id"+i);
			book.setPoster(Constants.SYSTEM_DIR + "poster" + i + ".jpg");
			book.setAuthor("author" + i);
			book.setDesc("desc" + i);
			book.setProgress(((i+1)%10)*10);
			book.setRating(((i+1)%10)*10);
			book.setTitle("title" + i);
			book.setTime("2013-10-10");
			book.setType(i%2);
			result.add(book);
		}
		return result;
	}
	
	public static List<List<Book>> getMainGridBooks(){
		List<List<Book>> result = new ArrayList<List<Book>>();
		for(int i=0;i<5;i++){
			List<Book> row = new ArrayList<Book>();
			for(int j=1;j<=3;j++){
				Book book =  new Book();
				int id = i*3 + j;
				book.setId("id"+id);
				book.setPoster(Constants.SYSTEM_DIR + "poster" + id + ".jpg");
				book.setAuthor("author" + id);
				book.setDesc("desc" + id);
				book.setProgress(60);
				book.setRating(80);
				book.setTitle("title" + id);
				book.setTime("2013-10-10");
				book.setType(id%2);
				row.add(book);
			}
			result.add(row);
		}
		return result;
	}
	
	public static Book getBookById(String id){
		for(Book b : getMainListBooks()){
			if(b.getId().equals(id))
				return b;
		}
		return new Book();
	}
	
	
	public static List<BookContent> getBookContents(){
		List<BookContent> result = new ArrayList<BookContent>();
		for(int i=1;i<10;i++){
			BookContent content = new BookContent();
			content.setId("content"+i);
			content.setTitle("标题xxx" + i);
			content.setPage(i-1);
			result.add(content);
		}
		return result;
	}
	
	public static List<User> getFriends(){
		List<User> result = new ArrayList<User>();
		for(int i=1;i<=12;i++){
			User user = new User();
			user.setId("user"+i);
			user.setDesc("descdescdesc"+i);
			user.setImage(Constants.SYSTEM_DIR + "poster" + i + ".jpg");
			user.setName("user" + i);
			user.setSex(i%2);
			result.add(user);
		}
		return result;
	}
	
	public static List<Plan> getMyPlan(){
		List<Plan> result = new ArrayList<Plan>();
		for(int i=1;i<=12;i++){
			Calendar calendar = Calendar.getInstance();
			Plan plan = new Plan();
			plan.setId("id" + i);
			plan.setBookName("book" + i);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-i);
			plan.setStartDate(calendar.getTime());
			calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			plan.setEndDate(calendar.getTime());
			plan.setProgress((i%10)*10);
			result.add(plan);
		}
		return result;
	}
	
	public static List<Purchase> getMyPurchae(){
		List<Purchase> result = new ArrayList<Purchase>();
		for(int i=1;i<=12;i++){
			Purchase book =  new Purchase();
			book.setId("id"+i);
			book.setPoster(Constants.SYSTEM_DIR + "poster" + i + ".jpg");
			book.setAuthor("author" + i);
			book.setDesc("desc" + i);
			book.setProgress(((i+1)%10)*10);
			book.setRating(((i+1)%10)*10);
			book.setTitle("title" + i);
			book.setTime("2013-10-10");
			book.setPrice(1*10);
			result.add(book);
		}
		return result;
	}
	
	public static List<Order> getOrders(int pay){
		List<Order> result = new ArrayList<Order>();
		for(int i=1;i<=12;i++){
			Order order = new Order();
			order.setId("No12345678" + i);
			order.setPrice(10*i);
			order.setStatus(pay);
			order.setTime("2013-06-03");
			result.add(order);
		}
		return result;
	}
	public static Order getOrderById(String orderId){
		List<Order> orderList = getOrders(1);
		for(Order o : orderList){
			if(o.getId().equals(orderId))
				return o;
		}
		return null;
	}
	
	public static List<DownloadBook> getDownLoadList(){
		List<DownloadBook> result = new ArrayList<DownloadBook>();
		
		for(int i=1;i<=12;i++){
			DownloadBook book = new DownloadBook();
			book.setId("id" + i);
			book.setTitle("Title " + i);
			book.setDesc("descdescdesc " + i);
			book.setDownloadProgress((i%10) * 10);
			book.setPoster(Constants.SYSTEM_DIR + "poster" + i + ".jpg");
			book.setSubBook(i%2==0);
			book.setSubTitle("SubTitle " + i);
			book.setTime("2013-06-05");
			result.add(book);
		}
		
		return result;
	}
}
