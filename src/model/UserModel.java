/*
 * 用户表数据模型，完成对用户表的各种操作
 */
package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserModel {

	public int checkUser(String uid,String p)
	{
		int result=1;  //1成功，2账户不存在，3密码错误,4异常
		String userroot="f:/"+uid+".txt";
		File f=new File(userroot);
		if(f.exists()==false)
		{
			result=2;
		}else{
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr=new FileReader(f);
				br=new BufferedReader(fr);
				String n=null;
				String[] nf=null;
				String pass=null;
				if((n=br.readLine())!=null)
				{
					nf=n.split(":");
					if(nf[0].equals("pass"))
					{
						pass=nf[1];
						if(pass.equals(p))
						{
							result=1;
						}else{
							result=3;
						}
					}else{
						result=4;
					}
				}else{
					result=4;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public void AddUser(JDialog owner)
	{
		AddDialog adddialog=new AddDialog(owner, true);
	}
	
	public void UpUser(JDialog owner)
	{
		UpDialog adddialog=new UpDialog(owner, true);
	}
	
}

class AddDialog extends JDialog implements ActionListener{

	JLabel jl1,jl2,jl3;
	JButton jb1,jb2;
	JTextField jtf1;
	JPasswordField jpass1,jpass2;
	JPanel jp1,jp2,jp3;
	
	//owner--它的父窗口;title--窗口名;model--指定是模式窗口，还是非模式，模式窗口必须响应
	public AddDialog(JDialog owner,boolean modal){
		super(owner,"注册用户",modal);                                                             //调用父类构造方法，达到模式对话框效果
		jl1=new JLabel("账号");
		jl2=new JLabel("密码");
		jl3=new JLabel("确认密码");

		jtf1=new JTextField();
		jpass1=new JPasswordField();
		jpass2=new JPasswordField();

		jb1=new JButton("添加");

		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(3,1));
		jp2.setLayout(new GridLayout(3,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		
		jp2.add(jtf1);
		jp2.add(jpass1);
		jp2.add(jpass2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//展现
		this.setSize(300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//用户点击添加按钮后的响应动作
		if(e.getSource()==jb1){
			FileWriter fw=null;
			BufferedWriter bw=null;
			String user=jtf1.getText();
			String pass1=new String(jpass1.getPassword());
			String pass2=new String(jpass2.getPassword());
			String userroot="f:/"+user+".txt";
			if(pass1.equals(pass2))
			{
				try {
					File f=new File(userroot);
					if(f.exists()==false)
					{
						f.createNewFile();
						fw=new FileWriter(f);
						bw=new BufferedWriter(fw);
						bw.write("pass:"+pass1);
						JOptionPane.showMessageDialog(this, "注册成功");		
					}else{
						JOptionPane.showMessageDialog(this, "账户已存在");		
					}
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}finally{
					try {
						bw.close();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				this.dispose();	
			}else{
				JOptionPane.showMessageDialog(this, "两次所输入密码不一致");
			}
		}
		else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}

class UpDialog extends JDialog implements ActionListener{

	JLabel jl1,jl2,jl3,jl4;
	JButton jb1,jb2;
	JTextField jtf1;
	JPasswordField jpass1,jpass2,jpass3;
	JPanel jp1,jp2,jp3;
	
	//owner--它的父窗口;title--窗口名;model--指定是模式窗口，还是非模式，模式窗口必须响应
	public UpDialog(JDialog owner,boolean modal){
		super(owner,"修改密码",modal);                                                             //调用父类构造方法，达到模式对话框效果
		jl1=new JLabel("账号");
		jl2=new JLabel("旧密码");
		jl3=new JLabel("新密码");
		jl3=new JLabel("确认密码");
			
		jtf1=new JTextField();
		jpass1=new JPasswordField();
		jpass2=new JPasswordField();
		jpass3=new JPasswordField();

		jb1=new JButton("修改");

		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(4,1));
		jp2.setLayout(new GridLayout(4,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		
		jp2.add(jtf1);
		jp2.add(jpass1);
		jp2.add(jpass2);
		jp2.add(jpass3);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//展现
		this.setSize(350, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//用户点击添加按钮后的响应动作
		if(e.getSource()==jb1){
			FileWriter fw=null;
			FileReader fr=null;
			BufferedWriter bw=null;
			BufferedReader br=null;
			String user=jtf1.getText();
			String pass1=new String(jpass1.getPassword());
			String pass2=new String(jpass2.getPassword());
			String userroot="f:/"+user+".txt";
			
			
			try {
				File f=new File(userroot);
				if(f.exists()==false)
				{
					f.createNewFile();
					fw=new FileWriter(f);
					bw=new BufferedWriter(fw);
					bw.write("pass:"+pass1);
					JOptionPane.showMessageDialog(this, "注册成功");		
				}else{
					JOptionPane.showMessageDialog(this, "账户已存在");		
				}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}finally{
				try {
					bw.close();
					br.close();
					fw.close();
					fr.close();
				} catch (IOException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.dispose();
		}
		else if(e.getSource()==jb2){
			this.dispose();
		}
	}
	
}