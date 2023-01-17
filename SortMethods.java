import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Michelle Dong
 *	@since	1/10/23
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		for(int i=0;i<arr.size();i++)
			for(int j=i;j<arr.size();j++)
				if(arr.get(i).getPopulation()>arr.get(j).getPopulation()) swap(arr,i,j);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City z=arr.get(x);
		arr.set(x,arr.get(y));
		arr.set(y,z);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void selectionSort(List<City> arr) {
		for(int i=arr.size();i>1;i--){
			int max=0;
			for(int j=1;j<i;j++)
				if(arr.get(j).compareTo(arr.get(max))>0) max=j;
			swap(arr,max,i-1);
		}
	}

	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr) {
		for(int i=1;i<arr.size();i++){
			City next=arr.get(i);
			int j=i;
			while(j>0&&next.getName().compareTo(arr.get(j-1).getName())<0){
				arr.set(j,arr.get(j-1));
				j--;
			}
			arr.set(j,next);
		}
	}
	/**
	 *	Merge Sort algorithm - in ascending order (you implement) for the names only
	 *	@param arr		array of City objects to sort
	 */
	private List<City> tempR;
	public void mergeSortR(List<City> arr) {
		int i=arr.size();
		tempR=new ArrayList<City>();
		for(int j=0;j<arr.size();j++) tempR.add(arr.get(j));
		recursiveR(arr,0,i-1);
	}
	/**
	 *	recursive  seperates in half and keeps recursing and merging eventually after the small intervals of the arraylist is sorted
	 *	@param arr		array of City objects to sort
	 *  @param start    the starting index of the interval
	 *  @param end      the ending index of the interval
	 */
	public void recursiveR(List<City> arr, int start, int end){
		if(end-start<2){
			if(end>start&&arr.get(end).getName().compareTo(arr.get(start).getName())<0) swap(arr,start,end);
		}
		else{
			int middle=(start+end)/2;
			recursiveR(arr,start,middle);
			recursiveR(arr,middle+1,end);
			mergeR(arr,start,middle,end);
		}
	}
	/**
	 *	merge  seperates in half and keeps recursing and merging eventually after the small intervals of the arraylist is sorted
	 *	@param arr		array of City objects to sort
	 *  @param start    the starting index of the interval
	 *  @param middle   the middle point where the first set has to stop and the second set starts
	 *  @param end      the ending index of the interval
	 */
	public void mergeR(List<City> arr,int start, int middle, int end){
		int i=start,j=middle+1,k=start;
		while(i<=middle&&j<=end){
			if(arr.get(i).getName().compareTo(arr.get(j).getName())<0){
				tempR.set(k,arr.get(i));
				i++;
			}
			else{
				tempR.set(k,arr.get(j));
				j++;
			}
			k++;
		}
		while(i<=middle){
			tempR.set(k,arr.get(i));
			i++;
			k++;
		}
		while(j<=end){
			tempR.set(k,arr.get(j));
			j++;
			k++;
		}
		for(k=start;k<=end;k++) arr.set(k,tempR.get(k));
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	private List<City> temp;
	public void mergeSort(List<City> arr) {
		int i=arr.size();
		temp=new ArrayList<City>();
		for(int j=0;j<arr.size();j++) temp.add(arr.get(j));
		recursive(arr,0,i-1);
	}
	/**
	 *	recursive  seperates in half and keeps recursing and merging eventually after the small intervals of the arraylist is sorted
	 *	@param arr		array of City objects to sort
	 *  @param start    the starting index of the interval
	 *  @param end      the ending index of the interval
	 */
	public void recursive(List<City> arr, int start, int end){
		if(end-start<2){
			City e=arr.get(end);
			City s=arr.get(start);
			if(end>start&&e.compareTo(s)<0) swap(arr,start,end);
		}
		else{
			int middle=(start+end)/2;
			recursive(arr,start,middle);
			recursive(arr,middle+1,end);
			merge(arr,start,middle,end);
		}
	}
	/**
	 *	merge  seperates in half and keeps recursing and merging eventually after the small intervals of the arraylist is sorted
	 *	@param arr		array of City objects to sort
	 *  @param start    the starting index of the interval
	 *  @param middle   the middle point where the first set has to stop and the second set starts
	 *  @param end      the ending index of the interval
	 */
	public void merge(List<City> arr,int start, int middle, int end){
		int i=start,j=middle+1,k=start;
		while(i<=middle&&j<=end){
			if(arr.get(i).compareTo(arr.get(j))<0){
				temp.set(k,arr.get(i));
				i++;
			}
			else{
				temp.set(k,arr.get(j));
				j++;
			}
			k++;
		}
		while(i<=middle){
			temp.set(k,arr.get(i));
			i++;
			k++;
		}
		while(j<=end){
			temp.set(k,arr.get(j));
			j++;
			k++;
		}
		for(k=start;k<=end;k++) arr.set(k,temp.get(k));
	}
}
