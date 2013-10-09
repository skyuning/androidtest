//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class HomeFragment extends Fragment{
//
//    View view;
// 
//    String[] presidents = { 
//                "Dwight D. Eisenhower", 
//                "John F. Kennedy", 
//                "Lyndon B. Johnson", 
//                "Richard Nixon", 
//                "Gerald Ford", 
//                "Jimmy Carter", 
//                "Ronald Reagan", 
//                "George H. W. Bush", 
//                "Bill Clinton", 
//                "George W. Bush", 
//                "Barack Obama" 
//            }; 
//     
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//                // TODO Auto-generated method stub
//                view = inflater.inflate(R.layout.fragment_tab1,container,false);
//                Init();
//                return view ;
//    }
//    
//    protected void Init(){
//                
//        ListView list = (ListView)view.findViewById(android.R.id.list);
//        
//        list.setAdapter(new ArrayAdapter<String>(getActivity(), 
//                android.R.layout.simple_list_item_1, presidents));
//        list.setOnItemClickListener(listener);
//    }
//    
//    private OnItemClickListener listener = new OnItemClickListener() {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position,
//                long id) {
//            // TODO Auto-generated method stub
//            Intent intent = new Intent(getActivity(), ListContainActivity.class);
//            
//            intent.putExtra("PRESIDENTS_CONTENT",presidents[position]);
//
//            getActivity().startActivity(intent);    
//        }   
//    };
//    
//}
