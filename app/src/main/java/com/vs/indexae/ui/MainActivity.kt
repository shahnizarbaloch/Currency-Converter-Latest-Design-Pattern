package com.vs.indexae.ui

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.vs.indexae.adapter.ItemAdapter
import com.vs.indexae.databinding.ActivityMainBinding
import com.vs.indexae.model.ItemModel
import com.vs.indexae.model.ServerRates
import com.vs.indexae.usage.Constants
import com.vs.indexae.usage.MySharedPreferences
import com.vs.indexae.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ItemAdapter.OnMyOwnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels()
    @Inject
    lateinit var application: BaseApplication
    private lateinit var offlineList:MutableList<ServerRates>
    private lateinit var adapter:ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeComponents()
    }

    /**
     * method to initialize the components.
     */
    private fun initializeComponents() {
        offlineList = ArrayList()

        viewModel.getListOffline()?.observe(this@MainActivity){
            if (it != null && it.isNotEmpty()) {
                offlineList= it
                //Show recyclerview here.
                showRecyclerView(offlineList,0)
            }
            else{
                viewModel.getLatestRateFromServer()
            }

        }

        /*binding.btnGridView.setOnClickListener {
            showRecyclerView(offlineList,0)
        }

        binding.btnListView.setOnClickListener {
            showRecyclerView(offlineList,1)
        }*/
        setSpinnerView()

        CoroutineScope(Dispatchers.Main).launch {

            if (offlineList.isEmpty()){
                viewModel.observeBundleCounter().observe(this@MainActivity){
                    addValuesInRoomDatabase(it)
                    binding.tvDateTime.text = it?.date?:"Error"
                    binding.tvSelectedCurrency.text = it?.base?:"Euro"
                    MySharedPreferences.setStringValue(application,Constants.DEFAULT_CURRENCY,it?.base?:"Euro")
                    MySharedPreferences.setStringValue(application,Constants.RETRIEVED_DATE,it?.date?:"Error")
                }
            }
        }

        viewModel.getIsLoading().observe(this@MainActivity){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.tvDateTime.text = MySharedPreferences.getStringValue(application,Constants.RETRIEVED_DATE,"Error")
        binding.tvSelectedCurrency.text = MySharedPreferences.getStringValue(application,Constants.DEFAULT_CURRENCY,"Euro")

    }

    private fun setSpinnerView() {
        val bankNames = arrayOf("Grid View", "List View")
        binding.simpleSpinner.onItemSelectedListener = this

        //Creating the ArrayAdapter instance having the bank name list

        //Creating the ArrayAdapter instance having the bank name list
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(this, R.layout.simple_spinner_item, bankNames)
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        //Setting the ArrayAdapter data on the Spinner
        binding.simpleSpinner.adapter = aa
    }

    /**
     * method to set recyclerview into the main activity
     */
    private fun showRecyclerView(offlineList: MutableList<ServerRates>,style:Int) {
        adapter = ItemAdapter(application,offlineList, this)
        binding.rv.adapter = adapter
        if(style==0){
            binding.rv.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }else{
            binding.rv.layoutManager = GridLayoutManager(this@MainActivity, 1)
        }

    }

    /**
     * method to insert the data into local room database
     */
    private fun addValuesInRoomDatabase(it: ItemModel?) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insert(ServerRates(1,"AED",it?.rates?.AED ?:0.0))
            viewModel.insert(ServerRates(2,"AFN",it?.rates?.AFN?:0.0))
            viewModel.insert(ServerRates(3,"ALL",it?.rates?.ALL?:0.0))
            viewModel.insert(ServerRates(4,"AMD",it?.rates?.AMD?:0.0))
            viewModel.insert(ServerRates(5,"ANG",it?.rates?.ANG?:0.0))
            viewModel.insert(ServerRates(6,"AOA",it?.rates?.AOA?:0.0))
            viewModel.insert(ServerRates(7,"ARS",it?.rates?.ARS?:0.0))
            viewModel.insert(ServerRates(8,"AUD",it?.rates?.AUD?:0.0))
            viewModel.insert(ServerRates(9,"AWG",it?.rates?.AWG?:0.0))
            viewModel.insert(ServerRates(10,"AZN",it?.rates?.AZN?:0.0))
            viewModel.insert(ServerRates(11,"BAM",it?.rates?.BAM?:0.0))
            viewModel.insert(ServerRates(12,"BBD",it?.rates?.BBD?:0.0))
            viewModel.insert(ServerRates(13,"BDT",it?.rates?.BDT?:0.0))
            viewModel.insert(ServerRates(14,"BGN",it?.rates?.BGN?:0.0))
            viewModel.insert(ServerRates(15,"BHD",it?.rates?.BHD?:0.0))
            viewModel.insert(ServerRates(16,"BIF",it?.rates?.BIF?:0.0))
            viewModel.insert(ServerRates(17,"BMD",it?.rates?.BMD?:0.0))
            viewModel.insert(ServerRates(18,"BND",it?.rates?.BND?:0.0))
            viewModel.insert(ServerRates(19,"BOB",it?.rates?.BOB?:0.0))
            viewModel.insert(ServerRates(20,"BRL",it?.rates?.BRL?:0.0))
            viewModel.insert(ServerRates(21,"BSD",it?.rates?.BSD?:0.0))
            viewModel.insert(ServerRates(22,"BTC",it?.rates?.BTC?:0.0))
            viewModel.insert(ServerRates(23,"BTN",it?.rates?.BTN?:0.0))
            viewModel.insert(ServerRates(24,"BWP",it?.rates?.BWP?:0.0))
            viewModel.insert(ServerRates(25,"BYN",it?.rates?.BYN?:0.0))
            viewModel.insert(ServerRates(26,"BYR",it?.rates?.BYR?:0.0))
            viewModel.insert(ServerRates(27,"BZD",it?.rates?.BZD?:0.0))
            viewModel.insert(ServerRates(28,"CAD",it?.rates?.CAD?:0.0))
            viewModel.insert(ServerRates(29,"CDF",it?.rates?.CDF?:0.0))
            viewModel.insert(ServerRates(30,"CHF",it?.rates?.CHF?:0.0))
            viewModel.insert(ServerRates(31,"CLF",it?.rates?.CLF?:0.0))
            viewModel.insert(ServerRates(32,"CLP",it?.rates?.CLP?:0.0))
            viewModel.insert(ServerRates(33,"CNY",it?.rates?.CNY?:0.0))
            viewModel.insert(ServerRates(34,"COP",it?.rates?.COP?:0.0))
            viewModel.insert(ServerRates(35,"CRC",it?.rates?.CRC?:0.0))
            viewModel.insert(ServerRates(36,"CUC",it?.rates?.CUC?:0.0))
            viewModel.insert(ServerRates(37,"CUP",it?.rates?.CUP?:0.0))
            viewModel.insert(ServerRates(38,"CVE",it?.rates?.CVE?:0.0))
            viewModel.insert(ServerRates(39,"CZK",it?.rates?.CZK?:0.0))
            viewModel.insert(ServerRates(40,"DJF",it?.rates?.DJF?:0.0))
            viewModel.insert(ServerRates(41,"DKK",it?.rates?.DKK?:0.0))
            viewModel.insert(ServerRates(42,"DOP",it?.rates?.DOP?:0.0))
            viewModel.insert(ServerRates(43,"DZD",it?.rates?.DZD?:0.0))
            viewModel.insert(ServerRates(44,"EGP",it?.rates?.EGP?:0.0))
            viewModel.insert(ServerRates(45,"ERN",it?.rates?.ERN?:0.0))
            viewModel.insert(ServerRates(46,"ETB",it?.rates?.ETB?:0.0))
            viewModel.insert(ServerRates(47,"FJD",it?.rates?.FJD?:0.0))
            viewModel.insert(ServerRates(48,"FKP",it?.rates?.FKP?:0.0))
            viewModel.insert(ServerRates(49,"GBP",it?.rates?.GBP?:0.0))
            viewModel.insert(ServerRates(50,"GEL",it?.rates?.GEL?:0.0))
            viewModel.insert(ServerRates(51,"GGP",it?.rates?.GGP?:0.0))
            viewModel.insert(ServerRates(52,"GHS",it?.rates?.GHS?:0.0))
            viewModel.insert(ServerRates(53,"GIP",it?.rates?.GIP?:0.0))
            viewModel.insert(ServerRates(54,"GMD",it?.rates?.GMD?:0.0))
            viewModel.insert(ServerRates(55,"GNF",it?.rates?.GNF?:0.0))
            viewModel.insert(ServerRates(56,"GTQ",it?.rates?.GTQ?:0.0))
            viewModel.insert(ServerRates(57,"GYD",it?.rates?.GYD?:0.0))
            viewModel.insert(ServerRates(58,"HKD",it?.rates?.HKD?:0.0))
            viewModel.insert(ServerRates(59,"HNL",it?.rates?.HNL?:0.0))
            viewModel.insert(ServerRates(60,"HRK",it?.rates?.HRK?:0.0))
            viewModel.insert(ServerRates(61,"HTG",it?.rates?.HTG?:0.0))
            viewModel.insert(ServerRates(62,"HUF",it?.rates?.HUF?:0.0))
            viewModel.insert(ServerRates(63,"IDR",it?.rates?.IDR?:0.0))
            viewModel.insert(ServerRates(64,"ILS",it?.rates?.ILS?:0.0))
            viewModel.insert(ServerRates(65,"IMP",it?.rates?.IMP?:0.0))
            viewModel.insert(ServerRates(66,"INR",it?.rates?.INR?:0.0))
            viewModel.insert(ServerRates(67,"IQD",it?.rates?.IQD?:0.0))
            viewModel.insert(ServerRates(68,"IRR",it?.rates?.IRR?:0.0))
            viewModel.insert(ServerRates(69,"ISK",it?.rates?.ISK?:0.0))
            viewModel.insert(ServerRates(70,"JEP",it?.rates?.JEP?:0.0))
            viewModel.insert(ServerRates(71,"JMD",it?.rates?.JMD?:0.0))
            viewModel.insert(ServerRates(72,"JOD",it?.rates?.JOD?:0.0))
            viewModel.insert(ServerRates(73,"JPY",it?.rates?.JPY?:0.0))
            viewModel.insert(ServerRates(74,"KES",it?.rates?.KES?:0.0))
            viewModel.insert(ServerRates(75,"KGS",it?.rates?.KGS?:0.0))
            viewModel.insert(ServerRates(76,"KHR",it?.rates?.KHR?:0.0))
            viewModel.insert(ServerRates(77,"KMF",it?.rates?.KMF?:0.0))
            viewModel.insert(ServerRates(78,"KPW",it?.rates?.KPW?:0.0))
            viewModel.insert(ServerRates(79,"KRW",it?.rates?.KRW?:0.0))
            viewModel.insert(ServerRates(80,"KWD",it?.rates?.KWD?:0.0))
            viewModel.insert(ServerRates(81,"KYD",it?.rates?.KYD?:0.0))
            viewModel.insert(ServerRates(82,"KZT",it?.rates?.KZT?:0.0))
            viewModel.insert(ServerRates(83,"LAK",it?.rates?.LAK?:0.0))
            viewModel.insert(ServerRates(84,"LBP",it?.rates?.LBP?:0.0))
            viewModel.insert(ServerRates(85,"LKR",it?.rates?.LKR?:0.0))
            viewModel.insert(ServerRates(86,"LRD",it?.rates?.LRD?:0.0))
            viewModel.insert(ServerRates(87,"LSL",it?.rates?.LSL?:0.0))
            viewModel.insert(ServerRates(88,"LTL",it?.rates?.LTL?:0.0))
            viewModel.insert(ServerRates(89,"LVL",it?.rates?.LVL?:0.0))
            viewModel.insert(ServerRates(90,"LYD",it?.rates?.LYD?:0.0))
            viewModel.insert(ServerRates(91,"MAD",it?.rates?.MAD?:0.0))
            viewModel.insert(ServerRates(92,"MDL",it?.rates?.MDL?:0.0))
            viewModel.insert(ServerRates(93,"MGA",it?.rates?.MGA?:0.0))
            viewModel.insert(ServerRates(94,"MKD",it?.rates?.MKD?:0.0))
            viewModel.insert(ServerRates(95,"MMK",it?.rates?.MMK?:0.0))
            viewModel.insert(ServerRates(96,"MNT",it?.rates?.MNT?:0.0))
            viewModel.insert(ServerRates(97,"MOP",it?.rates?.MOP?:0.0))
            viewModel.insert(ServerRates(98,"MRO",it?.rates?.MRO?:0.0))
            viewModel.insert(ServerRates(99,"MUR",it?.rates?.MUR?:0.0))
            viewModel.insert(ServerRates(100,"MVR",it?.rates?.MVR?:0.0))
            viewModel.insert(ServerRates(101,"MWK",it?.rates?.MWK?:0.0))
            viewModel.insert(ServerRates(102,"MXN",it?.rates?.MXN?:0.0))
            viewModel.insert(ServerRates(103,"MYR",it?.rates?.MYR?:0.0))
            viewModel.insert(ServerRates(104,"MZN",it?.rates?.MZN?:0.0))
            viewModel.insert(ServerRates(105,"NAD",it?.rates?.NAD?:0.0))
            viewModel.insert(ServerRates(106,"NGN",it?.rates?.NGN?:0.0))
            viewModel.insert(ServerRates(107,"NIO",it?.rates?.NIO?:0.0))
            viewModel.insert(ServerRates(108,"NOK",it?.rates?.NOK?:0.0))
            viewModel.insert(ServerRates(109,"NPR",it?.rates?.NPR?:0.0))
            viewModel.insert(ServerRates(110,"NZD",it?.rates?.NZD?:0.0))
            viewModel.insert(ServerRates(111,"OMR",it?.rates?.OMR?:0.0))
            viewModel.insert(ServerRates(112,"PAB",it?.rates?.PAB?:0.0))
            viewModel.insert(ServerRates(113,"PEN",it?.rates?.PEN?:0.0))
            viewModel.insert(ServerRates(114,"PGK",it?.rates?.PGK?:0.0))
            viewModel.insert(ServerRates(115,"PHP",it?.rates?.PHP?:0.0))
            viewModel.insert(ServerRates(116,"PKR",it?.rates?.PKR?:0.0))
            viewModel.insert(ServerRates(117,"PLN",it?.rates?.PLN?:0.0))
            viewModel.insert(ServerRates(118,"PYG",it?.rates?.PYG?:0.0))
            viewModel.insert(ServerRates(119,"QAR",it?.rates?.QAR?:0.0))
            viewModel.insert(ServerRates(120,"RON",it?.rates?.RON?:0.0))
            viewModel.insert(ServerRates(121,"RSD",it?.rates?.RSD?:0.0))
            viewModel.insert(ServerRates(122,"RUB",it?.rates?.RUB?:0.0))
            viewModel.insert(ServerRates(123,"RWF",it?.rates?.RWF?:0.0))
            viewModel.insert(ServerRates(124,"SAR",it?.rates?.SAR?:0.0))
            viewModel.insert(ServerRates(125,"SBD",it?.rates?.SBD?:0.0))
            viewModel.insert(ServerRates(126,"SCR",it?.rates?.SCR?:0.0))
            viewModel.insert(ServerRates(127,"SDG",it?.rates?.SDG?:0.0))
            viewModel.insert(ServerRates(128,"SEK",it?.rates?.SEK?:0.0))
            viewModel.insert(ServerRates(129,"SGD",it?.rates?.SGD?:0.0))
            viewModel.insert(ServerRates(130,"SHP",it?.rates?.SHP?:0.0))
            viewModel.insert(ServerRates(131,"SLL",it?.rates?.SLL?:0.0))
            viewModel.insert(ServerRates(132,"SOS",it?.rates?.SOS?:0.0))
            viewModel.insert(ServerRates(133,"SRD",it?.rates?.SRD?:0.0))
            viewModel.insert(ServerRates(134,"STD",it?.rates?.STD?:0.0))
            viewModel.insert(ServerRates(135,"SVC",it?.rates?.SVC?:0.0))
            viewModel.insert(ServerRates(136,"SYP",it?.rates?.SYP?:0.0))
            viewModel.insert(ServerRates(137,"SZL",it?.rates?.SZL?:0.0))
            viewModel.insert(ServerRates(138,"THB",it?.rates?.THB?:0.0))
            viewModel.insert(ServerRates(139,"TJS",it?.rates?.TJS?:0.0))
            viewModel.insert(ServerRates(140,"TMT",it?.rates?.TMT?:0.0))
            viewModel.insert(ServerRates(141,"TND",it?.rates?.TND?:0.0))
            viewModel.insert(ServerRates(142,"TOP",it?.rates?.TOP?:0.0))
            viewModel.insert(ServerRates(143,"TRY",it?.rates?.TRY?:0.0))
            viewModel.insert(ServerRates(144,"TTD",it?.rates?.TTD?:0.0))
            viewModel.insert(ServerRates(145,"TWD",it?.rates?.TWD?:0.0))
            viewModel.insert(ServerRates(146,"TZS",it?.rates?.TZS?:0.0))
            viewModel.insert(ServerRates(147,"UAH",it?.rates?.UAH?:0.0))
            viewModel.insert(ServerRates(148,"UGX",it?.rates?.UGX?:0.0))
            viewModel.insert(ServerRates(149,"USD",it?.rates?.USD?:0.0))
            viewModel.insert(ServerRates(150,"UYU",it?.rates?.UYU?:0.0))
            viewModel.insert(ServerRates(151,"UZS",it?.rates?.UZS?:0.0))
            viewModel.insert(ServerRates(152,"VND",it?.rates?.VND?:0.0))
            viewModel.insert(ServerRates(153,"VUV",it?.rates?.VUV?:0.0))
            viewModel.insert(ServerRates(154,"WST",it?.rates?.WST?:0.0))
            viewModel.insert(ServerRates(155,"XAF",it?.rates?.XAF?:0.0))
            viewModel.insert(ServerRates(156,"XAG",it?.rates?.XAG?:0.0))
            viewModel.insert(ServerRates(157,"XAU",it?.rates?.XAU?:0.0))
            viewModel.insert(ServerRates(158,"XCD",it?.rates?.XCD?:0.0))
            viewModel.insert(ServerRates(159,"XDR",it?.rates?.XDR?:0.0))
            viewModel.insert(ServerRates(160,"XOF",it?.rates?.XOF?:0.0))
            viewModel.insert(ServerRates(161,"XPF",it?.rates?.XPF?:0.0))
            viewModel.insert(ServerRates(162,"YER",it?.rates?.YER?:0.0))
            viewModel.insert(ServerRates(163,"ZAR",it?.rates?.ZAR?:0.0))
            viewModel.insert(ServerRates(164,"ZMK",it?.rates?.ZMK?:0.0))
            viewModel.insert(ServerRates(165,"ZMW",it?.rates?.ZMW?:0.0))
            viewModel.insert(ServerRates(166,"ZWL",it?.rates?.ZWL?:0.0))
        }
    }

    override fun onMyOwnClick(position: Int) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        showRecyclerView(offlineList,position)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}