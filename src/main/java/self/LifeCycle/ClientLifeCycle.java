package self.LifeCycle;

import self.LifeCycle.States.ActivateState;
import self.LifeCycle.States.HoldState;
import self.LifeCycle.States.ShutdownState;

public class ClientLifeCycle {
	
	public boolean loginflag;
	public boolean Androidflag;
	
	public void isLogin(boolean result, boolean androidflag) {
		
		if(result) {
			
			//if there is an Android
			if(androidflag) {
				
				moveLifeCycle(new ActivateState());

			} else {
				
				moveLifeCycle(new HoldState());	
			}
			
		} else {
			
			//login fail
			moveLifeCycle(new ShutdownState());
		}
	}

	private LifeCycleState Currentstate;
	
	public LifeCycleState getCurrentState() {
		
		return Currentstate;
	}
	
	public void Loading() {
		
		
	}
	
	public void moveLifeCycle(LifeCycleState state) {
		
		if(Currentstate == null) {
			
			this.Currentstate = state;
			this.Currentstate.Start();
			
			return;
		} else {
			
			state.end();
			
			if(Currentstate.getClass().getSimpleName().equals(state.getClass().getSimpleName())) {
			
				//do nothing
			} else {
				
				this.Currentstate = state;
				state.Start();
			}
			
		}
		
	}
	
	

}
