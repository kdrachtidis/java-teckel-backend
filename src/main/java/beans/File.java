package beans;

public class File {

	private UploadedFile[] uploadedFiles;

	public File() {
	}

	public UploadedFile[] getFiles() {
		return uploadedFiles;
	}

	public void setFiles(UploadedFile[] files) {
		this.uploadedFiles = files;
	}

}
