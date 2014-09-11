/*
 To do the Forward Chaining
 Name : Garvit Bansal and Shakti Kheria
 College: LNMIIT Jaipur
 */

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class fwdchaining implements ActionListener
{
     JFrame window = new JFrame("Forward Chaining ");
     JMenuBar mnubar = new JMenuBar();
     JMenuItem  mnuitem1 = new JMenuItem("KB Table 1"),
       mnuitem2 = new JMenuItem("KB Table 2"),
       mnuitem3 = new JMenuItem("Fact Base"),
       mnuitem4 = new JMenuItem("KB Table 1"),
       mnuitem5 = new JMenuItem("KB Table 2"),
       mnuitem6 = new JMenuItem("Fact Base"),
       ruleprint = new JMenuItem("Knowledge Base"),
       select = new JMenuItem("Select FB"),
       add = new JMenuItem("Add FB"),
       modify = new JMenuItem("Modify FB"),
       insertown = new JMenuItem("Insert own"),
       mnuitem7 = new JMenuItem("Forward"),
       mnuitem8 = new JMenuItem("Quit"),
       mnuitem9 = new JMenuItem("Reasoning"),
       mnuitem10 = new JMenuItem("Software"),
       mnuitem11 = new JMenuItem("Author");
private Connection con;
     JPanel top=new JPanel(),
             bottom=new JPanel();

     JMenu mnu=new JMenu("Open"),
             view=new JMenu("View"),
             insert = new JMenu("Options"),
             chaining=new JMenu("Chaining"),
             howderived = new JMenu("Explanation"),
             about = new JMenu("About");
     JMenu exit=new JMenu("Exit");
     File sf1,sf2,sf3;
     JList factt;
     JTable table=new JTable();
     //JLabel select = new JLabel("Select the Database");
     JList list;
     String listData[] ={"Animal KB","Mobile Selection KB"};
     ActionListener action;
     Object[][] how_derived;
     String factre[],rule_no[]=new String[20];
     int re,index;
     int temp=0,ind; //to know the status of the button
    private Statement st12;
    private ResultSet rs12;
     // Start of the forward chianing
int num;
   String factlist[] = new String[50];
    private Statement st16;
    private ResultSet rs16;
     public fwdchaining()
     {
         // to set the properties of the widow frame
         window.setLayout(new BorderLayout());
         window.setResizable(false);
         window.setLocation(0,0);
         window.setSize(600,400);
         window.setVisible(true);
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // add the mnuitems into mnubar
         mnu.add(mnuitem1);
         mnu.addSeparator();
         mnu.add(mnuitem2);
         mnu.addSeparator();
         mnu.add(mnuitem3);
         mnubar.add(mnu);

         view.add(ruleprint);
         view.addSeparator();
         view.add(mnuitem4);
         view.addSeparator();
         view.add(mnuitem5);
         view.addSeparator();
         view.add(mnuitem6);
         mnubar.add(view);

         insert.add(select);
         insert.addSeparator();
         insert.add(add);
         insert.addSeparator();
         insert.add(modify);
         insert.addSeparator();
         insert.add(insertown);
         mnubar.add(insert);

         chaining.add(mnuitem7);
         mnubar.add(chaining);

         howderived.add(mnuitem9);
         mnubar.add(howderived);

         about.add(mnuitem10);
         about.addSeparator();
         about.add(mnuitem11);
         mnubar.add(about);
         
         exit.add(mnuitem8);
         mnubar.add(exit);

         window.setJMenuBar(mnubar);

         list = new JList(listData);
         
         top.setLayout(new BorderLayout());

         // add the panel top to the frame window
         window.add(top);
         // to perform the action on selection of the mnuitems

         mnuitem1.addActionListener((ActionListener) this);
         mnuitem2.addActionListener((ActionListener) this);
         mnuitem3.addActionListener((ActionListener) this);
         mnuitem4.addActionListener(this);
         mnuitem5.addActionListener(this);
         mnuitem6.addActionListener(this);
         mnuitem7.addActionListener(this);
         mnuitem8.addActionListener(this);
         mnuitem9.addActionListener(this);
         mnuitem10.addActionListener(this);
         mnuitem11.addActionListener(this);
         select.addActionListener(this);
         add.addActionListener(this);
         ruleprint.addActionListener(this);
         modify.addActionListener(this);
         insertown.addActionListener(this);
     }

     // the action performed method to perform the action specific to the selection of the choices

       public void actionPerformed(ActionEvent action)
       {
                Object source = action.getSource();
                if(source == mnuitem1)
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    Component parent=null;
                    int option=chooser.showOpenDialog(parent);
                    if (option == JFileChooser.APPROVE_OPTION)
                    {
                            sf1 = chooser.getSelectedFile();
                            temp++;
                    }
                }
    
                if(source == mnuitem2)
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    Component parent=null;
                    int option=chooser.showOpenDialog(parent);
                    if (option == JFileChooser.APPROVE_OPTION)
                    {
                            sf2 = chooser.getSelectedFile();
                            temp++;
                    }
                }

                if(source == mnuitem3)
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    Component parent=null;
                    int option=chooser.showOpenDialog(parent);
                    if (option == JFileChooser.APPROVE_OPTION)
                    {
                            sf3 = chooser.getSelectedFile();
                            temp++;
                    }
                }

                // for displaying the knowledge base
                if(source == ruleprint)
                {
                     top.removeAll();
                     top.repaint();
                     JTextArea txtarea = new JTextArea(40,50);
                     BufferedReader br;
                    try
                    {
                        br = new BufferedReader(new FileReader("C:\\Users\\striker\\Desktop\\knowledgebase1.txt"));
                        String line = null;
                        try
                        {
                            txtarea.append("-------------------------------------------------------------\n");
                            txtarea.append("           Knowledge Base:-     \n");
                            txtarea.append("-------------------------------------------------------------\n\n");
                            while ((line = br.readLine()) != null)
                            {
                                txtarea.append(line + "\n \n");
                            }
                            top.add(txtarea);
                            window.add(top);
                            window.pack();
                            br.close();
                        }
                        catch (IOException ex)
                        {
                            Logger.getLogger(fwdchaining.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    catch (FileNotFoundException ex)
                    {
                        Logger.getLogger(fwdchaining.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if(source == mnuitem4)
                {
                     top.removeAll();
                     top.repaint();

                     String query = "select * from kbtable1;";
                     display_tables(query);
                    
                }
                if(source == mnuitem5)
                {
                     top.repaint();
                     top.removeAll();
                     String query = "select * from kbtable2;";
                     display_tables(query);
                }

                if(source == mnuitem6)
                {
                     top.repaint();
                     top.removeAll();
                     try
                     {
                        if(con==null)
                            conect();
                         st12=con.createStatement();
                        String query = "select * from factbase;";
                        rs12 = st12.executeQuery(query);
                        JTextArea txtarea3 = new JTextArea(30,80);
                        txtarea3.setEditable(false);
                        txtarea3.append("\tFACTS\n");
                        txtarea3.append("--------------------------------------------------------\n\n");
                        while(rs12.next())
                        {
                              String qw = rs12.getString(1);
                              txtarea3.append(qw + "\n");
                        }
                          top.repaint();
                          top.removeAll();
                          top.add(txtarea3);
                          window.add(top);
                          window.pack();
                     }
                     catch(Exception e)
                     {
                            JOptionPane.showMessageDialog(null,"Error while displaying content of fact base");
                     }
                }

                // to insert into fact base
                if(source == select)
                {
                   top.repaint();
                   top.removeAll();
                    try
                    {
                        if(con==null)
                            conect();
                        st13 = con.createStatement();
                        String query = "select * from kbtable1;";
                        rs13 = st13.executeQuery(query);
                        num = 0;
                        while(rs13.next())
                        {
                            factlist[num] = rs13.getString(2);
                            //System.out.println(factlist[0]);
                            num++;
                        }
                        factt = new JList(factlist);
                        factt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        top.add(factt);
                        window.add(top);
                        window.pack();

                        
                    }
                     catch (SQLException ex)
                     {
                        Logger.getLogger(fwdchaining.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                }

                if(source == add)
                {
                    try
                    {
                        st15 = con.createStatement();
                        st15.executeUpdate("Delete from factbase");
                    }
                    
                    catch (SQLException ex)
                    {
                        Logger.getLogger(fwdchaining.class.getName()).log(Level.SEVERE, null, ex);
                    }

                     Object a[] = new Object[50];
                     a =  factt.getSelectedValues();
                     for(int k = 0 ; k < a.length ; k++)
                     {
                        try
                        {
                            if(con == null)
                                conect();
                            int ref;
                            st14 = con.createStatement();
                            st14.executeUpdate("Insert into factbase values('"+a[k]+"')");
                           // System.out.println(a[k]);
                        } 
                        catch (SQLException ex)
                        {
                            Logger.getLogger(fwdchaining.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }
                }

                if(source == modify)
                {
                    top.repaint();
                    top.removeAll();
                    Object selected = factt.getSelectedValue();
                    String entered_value = JOptionPane.showInputDialog("Enter the value to be entered");
                    if(con == null)
                      conect();
                    try
                    {
                        st17 = con.createStatement();
                        st17.executeUpdate("Update kbtable1 set facts ='"+entered_value+ "'where facts ='"+ selected+ "'");
                    }
                    catch (SQLException ex)
                    {
                         JOptionPane.showMessageDialog(null,"Error while displaying content of inference array");
                    }
                }

                if(source == insertown)
                {
                     top.repaint();
                    top.removeAll();
                    String entered_value = JOptionPane.showInputDialog("Enter the Fact Base:");
                    if(con == null)
                      conect();
                    try
                    {
                        st17 = con.createStatement();
                        st17.executeUpdate("Insert into factbase values('"+entered_value+"')");
                    }
                    catch (SQLException ex)
                    {
                         JOptionPane.showMessageDialog(null,"Error while displaying content of inference array");
                    }
                }

                if(source == mnuitem7)
                {
                     top.repaint();
                     top.removeAll();
                     re = 0;
                     ind = 0;
                     index=0;
                     firearray=new int[100];
                     how_derived = new Object[20][2];
                     factre =  new String[100];
                     forwardchaining();
                     try
                     {
                        st11 = con.createStatement();
                        rs11 = st11.executeQuery("select * from inferencearray");
                        JTextArea txtarea2 = new JTextArea(30,50);
                        txtarea2.setEditable(false);
                        while(rs11.next())
                        {
                              String qw = rs11.getString(1);
                              txtarea2.append(qw + "\n");
                        }
                          top.repaint();
                          top.removeAll();
                          top.add(txtarea2);
                          window.add(top);
                          window.pack();
                     }
                     catch(Exception e)
                     {
                         JOptionPane.showMessageDialog(null,"Error while displaying content of inference array");
                     }
                }

                if(source == mnuitem8)
                {
                    top.repaint();
                    top.removeAll();
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                       "Exit" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)
                          System.exit(0);
                }

                if(source == mnuitem9)
                {
                   try
                   {
                       FileWriter fstream = new FileWriter("C:\\Users\\striker\\Desktop\\derived.txt");
                       PrintWriter out =  new PrintWriter(fstream);
                       out.println("-------------------------------------------------------------");
                       out.println("    How the Infered Facts are derived:-     ");
                       out.println("-------------------------------------------------------------");
                       
                       for(int i=0;i<ind;i++)
                       {

                           out.println(rule_no[i]+ " : " + how_derived[i][1]+"  DERIVES  "+how_derived[i][0]);
                       }
                       out.println("\n-------------------------------------------------------------");
                       out.println("  References of Fact-Symbols:");
                       out.println("-------------------------------------------------------------");
                       for(int i=0;i<re;i++)
                           out.println(factre[i]);
                      st9 = con.createStatement();
                      rs9 = st9.executeQuery("select * from inferencearray");
                      
                      JTextArea txtarea = new JTextArea(40,50);
                      
                      while(rs9.next())
                      {
                          String qw = rs9.getString(1);
                          st10 = con.createStatement();
                          rs10 = st10.executeQuery("select indx from kbtable1 where facts='"+qw+"'");
                          String fact_index = null;
                          while(rs10.next())
                            fact_index = rs10.getString(1);
                          out.println(fact_index+"\t:\t"+qw);
                           //txtarea.append(fact_index+" : " +qw + "\n");
                      } 
                          out.close();

                          BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\striker\\Desktop\\derived.txt"));
                          String line = null;
                          while((line = br.readLine()) != null)
                          {
                              txtarea.append(line +"\n");
                          }

                          top.repaint();
                          top.removeAll();
                          top.add(txtarea);
                          window.add(top);
                          window.pack();
                          
                          br.close();
                   }
                   catch(Exception e)
                   {
                        JOptionPane.showMessageDialog(null,"Error while creating file");
                   }
                }

                if(source == mnuitem10)
                {
                    top.repaint();
                    top.removeAll();
                    JTextArea txtarea = new JTextArea(15,20);
                    String exp = "\n\t          ABOUT \n\t         *********\n" +
                            "            This Software is used to do forward chaining. It takes KBTable1, \n" +
                                    "   KBTable2 and FactBase as input and does processing on them to  \n" +
                                    "   give a set of inferred facts. We have also given the reasoning,i.e.how \n" +
                                    "   the inferred facts were actually inferred using the chain from basic to  \n" +
                                    "   derived facts.";
                    txtarea.append(exp);
                    top.add(txtarea);
                    window.add(top);
                    window.pack();
                }

                if(source == mnuitem11)
                {
                    top.repaint();
                    top.removeAll();
                    JTextArea txtarea = new JTextArea(15,20);
                    String exp = "\n\t          AUTHOR \n\t         ***********\n      This Software is made by Garvit Bansal and Shakti Kheria     \n  of The LNM Institute of Information Technology,Jaipur.";
                    txtarea.append(exp);
                    top.add(txtarea);
                    window.add(top);
                    window.pack();
                }
       }

       // private Connection con;
        private Statement st,st0,st1,st2,st3,st4,st5,st6,st7,st8,st9,st10,st11,st13,st14,st15,st17,st18,st19,st20;
        private ResultSet rs,rs0,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11,rs13,rs14,rs15,rs17,rs18,rs19,rs20;

        // connect with the database
        private void conect()
        {
          try
          {
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kdis","root","");
            st = con.createStatement();
          }catch(Exception ex)
          {
             JOptionPane.showMessageDialog(null,"Error while connecting to the database");
          }
        }

        // to display the table
        
       private void display_tables(String query)
       {
          conect();
          try
          {
             rs = st.executeQuery(query);
             while(rs.next())
             {
                 ResultSetMetaData md =rs.getMetaData();
                 int columncount=md.getColumnCount();
                 Vector data= new Vector();
                 Vector columnname=new Vector();
                 Vector row = new Vector(columncount);

                 for (int i=1;i<=columncount;i++)
                 {
                     columnname.addElement(md.getColumnName(i));
                 }
                 data.add(columnname);
                 data.add(new Vector());
                 do
                 {
                     row=new Vector();
                     for(int i=1;i<=columncount;i++)
                     {
                        // int width = rs.getString(i).length();
                         //table.getColumnModel().getColumn(i).setMaxWidth(width);
                         row.addElement(rs.getObject(i));
                     }
                     data.add(row);   
                 }while(rs.next());
                 //System.out.println(data);
                 DefaultTableModel model= new DefaultTableModel(data,columnname);
                 table.setModel(model);
                 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                 JTextArea are = new JTextArea();
                 are.setEditable(false);
                 top.add(are);
                 window.add(top);
                 window.pack();
                 top.repaint();
                 top.removeAll();
                 top.add(table);
                 window.add(top);
                 window.setBounds(0, 0,1200,500);
             }
         }catch(Exception ex)
         {
             System.out.println("Error");
         }
      }

       // ***************START OF THE forwardchaining*******************************

      private void forwardchaining()
      {
          conect();
          try
          {
            st0=con.createStatement();
            int qwerty = st0.executeUpdate("Delete from inferencearray");
            rs = st.executeQuery("select facts from factbase;");
            while(rs.next())
            {
                System.out.println("entereddfjhjdhjghjg");
                System.out.println(rs);
                 ResultSetMetaData md =rs.getMetaData();
                 int columncount=md.getColumnCount();
                 //String row;
                 do
                 {
                     String row;
                     for(int i=1;i<=columncount;i++)
                     {
                         row=(String) rs.getObject(i);
                         System.out.println("sasa="+row);
                         factsearch(row);
                     }
                 }while(rs.next());
            }
            // to print the fact
            
             for(int i=0;i<re;i++)
             {
                 System.out.println(factre[i]);
             }
          }
          catch(Exception e)
          {
            System.out.println("Error");
          }
      }

      private int firearray[]; // to add it automatically

      // to do the fact search for each fact
      
     private void factsearch(String row)
     {
         try
         {
             st1 = con.createStatement();
             rs1 = st1.executeQuery("select * from kbtable1 where facts='"+row+"'");
            while(rs1.next())
            {
                System.out.println("dada");
                String basic=rs1.getString(3);
                int basic_or_derived=Integer.parseInt(basic);
                if(basic_or_derived == 1)
                {
                    JOptionPane.showMessageDialog(null," "+row+"  is a Derived Fact.\n Hence,Forward chaining cannot be applied to it");
                    continue;
                }

                factre[re++] = rs1.getString(1)+ "\t:\t"+ row;

                String rules=rs1.getString(5);
                
                if(rules.equals("NIL"))
                    break;

                String rul[]=rules.split(",");

                for(String a : rul)
                {
                        String t=a.substring(1);
                        int ruleno=Integer.parseInt(t);
                        if(firearray[ruleno-1] == 0)
                            processfwdchainrule(a);
                }
            }
             
         }catch(Exception e)
         {
                JOptionPane.showMessageDialog(null, "Error while doing the Forward chaining");
         }
     }

     private void processfwdchainrule(String rule) throws SQLException
     {
         try
         {
             st2 = con.createStatement();
             rs2 = st2.executeQuery("Select * from kbtable2 where rule_name='"+rule+"'");
            while(rs2.next())
            {
                String dep_fact=rs2.getString(2);
                System.out.println(dep_fact);
                String dep_fact1[];
                if(dep_fact.indexOf('+') == -1)
                {
                      dep_fact1 = new String[1];
                      dep_fact1[0]=dep_fact;
                }
                else
                {
                    dep_fact1=dep_fact.split("\\+");
                    System.out.println(dep_fact1[0]+" "+dep_fact1[1]);
                }
                int flag=0;
                for(String a1 : dep_fact1)
                {
                    if(flag == 1)
                        break;
                    String dep_fact2[];
                   // System.out.println("a1="+a1);
                    if(a1.indexOf('.') == -1)
                    {
                        dep_fact2 = new String[1];
                        dep_fact2[0]=a1;
                    }
                    else
                    {
                      dep_fact2=a1.split("\\.");
                    }

                     int flag7=1;
                     int flag1;
                     for(String a2 : dep_fact2)
                     {
                         st3 = con.createStatement();
                         int flag6 = 0;
                         if((a2.substring(0,1)).equals("!"))
                         {
                             flag6=1;
                             a2=a2.substring(1);
                         }
                         
                         rs3 = st3.executeQuery("select facts from kbtable1 where indx='"+a2+"'");
                         while(rs3.next())
                         {
                             String fact_name = rs3.getString(1);
                             if(flag6 == 1)
                             {
                                 factre[re++] = a2 + "\t:\t" + fact_name;
                             }

                             st4 = con.createStatement();
                             rs4 = st4.executeQuery("select * from factbase where facts='"+fact_name+"'UNION select * from inferencearray where facts='"+fact_name+"'");
                             
                             if(rs4.next())
                                 flag1=1;
                             else
                                 flag1=0;
                             if((flag1==0)&&(flag6==0))
                             {
                                 flag7=0;
                                 break;
                             }
                             if((flag1==1)&&(flag6==1))
                             {
                                 flag7=0;
                                 break;
                             }
                         }
                         if(flag7 == 0)
                                 break;
                     }
                     if(flag7 == 1)
                         flag=1;
                }
                if(flag == 1)
                {
                    String t=rule.substring(1);
                    int ruleno=Integer.parseInt(t);
                    if(firearray[ruleno-1] == 0)
                    {
                         rule_no[index++] = rs2.getString(1);
                         String derived_fact = rs2.getString(4);
                         String dependent_fact = rs2.getString(2);
                         //System.out.println("Ruleno" +rule_no[index--]);

                        st20 = con.createStatement();
                        rs20 = st20.executeQuery("Select * from kbtable1 where indx='"+derived_fact+"'");
                         int i,fact_derived = 0;
                         String fact_split[] = dependent_fact.split("\\.");
                         String dependent_fact1=null;
                         for(String fact_split1: fact_split)
                         {
                                fact_derived=0;
                                dependent_fact1 = fact_split1;
                                String nu = "null";
                                for(i=0;i<20;i++)
                                {
                                     if(how_derived[i][0] == null)
                                         break;
                                    if(how_derived[i][0].equals(fact_split1))
                                    {
                                        fact_derived = 1;
                                        break;
                                    }     
                                }

                                if (fact_derived == 1)
                                {
                                    st19 = con.createStatement();
                                    rs19 = st19.executeQuery("Select * from kbtable1 where indx='"+fact_split1+"'");
                                    while(rs19.next())
                                    {
                                        dependent_fact1 = "(" + how_derived[i][1] + "  DERIVES " + rs19.getString(2)+")";
                                    }
                                }

                                while(rs20.next())
                                {
                                    how_derived[ind][0] = rs20.getString(2);
                                }
                                if(how_derived[ind][1] != null)
                                {
                                     st19 = con.createStatement();
                                    rs19 = st19.executeQuery("Select * from kbtable1 where indx='"+dependent_fact1+"'");
                                    while(rs19.next())
                                    {
                                        how_derived[ind][1] ="(" + how_derived[ind][1] +"   AND   "+rs19.getString(2) + ")";

                                    }//how_derived[ind][3] = rule_no;
                                }
                                else
                                {
                                    st19 = con.createStatement();
                                    rs19 = st19.executeQuery("Select * from kbtable1 where indx='"+dependent_fact1+"'");
                                    while(rs19.next())
                                    {
                                    how_derived[ind][1] = rs19.getString(2);
                                    }
                                    //how_derived[ind][3] = rule_no;
                                }
                                //System.out.println("Ruleno 1" + how_derived[ind][3]);
                         }
                         ind= ind +1;
                       
                         st5 = con.createStatement();
                         rs5 = st5.executeQuery("select facts from kbtable1 where indx='"+derived_fact+"'");
                         while(rs5.next())
                         {
                              String inf_fact = rs5.getString(1);
                              st6 = con.createStatement();
                              System.out.println("Fact "+inf_fact);
                              st7 = con.createStatement();
                              rs7 = st7.executeQuery("select * from inferencearray where facts='"+inf_fact+"'");
                              if(rs7.first());
                              else
                              {
                                int val = st6.executeUpdate("insert into inferencearray "+"values ('"+inf_fact+"')");
                              }
                              firearray[ruleno-1]=1;
                              String lead_to = rs2.getString(5);
                              if(lead_to.equals("NIL"))
                                break;
                              String lead_to_rule[]=lead_to.split(",");
                              for(String b: lead_to_rule)
                              {
                                  processfwdchainrule(b);
                              } 
                         }
                    }
                }
            }
         }catch(Exception e)
         {
                JOptionPane.showMessageDialog(null, "Error while doing the Forward chaining");
         }
     }
}