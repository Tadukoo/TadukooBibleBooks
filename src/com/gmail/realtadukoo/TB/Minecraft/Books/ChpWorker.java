package com.gmail.realtadukoo.TB.Minecraft.Books;

public class ChpWorker implements Runnable{	
	protected Queue<ChpWorkInfo> todo, done;
	
	public ChpWorker(Queue<ChpWorkInfo> todo, Queue<ChpWorkInfo> done){
		this.todo = todo;
		this.done = done;
	}

	@Override
	public void run(){
		boolean cont = true;
		while(cont){
			try{
				// Get the thread's work order from the todo queue
				ChpWorkInfo work = todo.dequeue();
				if(work.getChp() == -1){
					cont = false;
				}else{
					// Do some work
					work.setPages(GenerateBook.generateWholeChapter(work.getBook(), work.getChp(), work.getTran()));
					// Put the generated chapter into the done queue
					done.enqueue(work);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
