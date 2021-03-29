package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {
	
	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}
	
	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}
	
	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}
	
	public void run() {
	
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("Root Children:");
			System.out.println(child.getName());
			LinkedList<ApfsElement> child1Children =child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println(child1.getName());
				LinkedList<ApfsElement> child2Children =child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println(child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {
		
		APFS apfs = new APFS("APFS File System");
		
		apfs.initFileSystem("Local Disk", 10000);		
		ApfsDirectory root = apfs.getRootDir();
		
		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile f1, f2;		
		f1 = new ApfsFile(applications, "A", 50);
		f2 = new ApfsFile(applications, "B", 40);
		applications.appendChild(f1);
		applications.appendChild(f2);
		
		Thread t1 = new Thread(apfs);				
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ApfsDirectory home  = new ApfsDirectory(root, "home");
		root.appendChild((home));
		ApfsFile f3, f4;		
		f3 = new ApfsFile(home, "A", 100);
		f4 = new ApfsFile(home, "B", 200);
		home.appendChild(f3);
		home.appendChild(f4);
		
		Thread t2 = new Thread(apfs);				
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
		
	
	}
}
