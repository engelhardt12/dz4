import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class String getNick()
        {
    return nick;
        }


        public class ClientHandler(Server server,Socket socket)
{
    this.socket=socket;
    this.server=server;
    out=new DataOutputStream(socket.getOutputStream());
    in= new DataInputStream(socket.getInputStream());
    this.authService=new AuthServiceImpl();
    this.blacklist=new CopyOnWriteArrayList<>();
    Handler h=new FileHandler("ClientHandler.log",true);
    h.setFormatter(new SimpleFormatter());
    h.setLevel(Level.All);
    logger.addHandler(h);

    ExecutorService service=Exicutors.newCachedThreadPool();
    Future future=service.submit((new Callable<Object>()
    {
        public Object call()
        {
            try
            {
                autorization();
                sendMsg(History.loadHistory());
                server.broadcast(ClientHandler.this,getNick()+" ");
                logger.info(getNick()+" ");
                read();

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }finally
            {
               close();
            }
            return " ";
        }
    }));
    service.shutdown();
}
