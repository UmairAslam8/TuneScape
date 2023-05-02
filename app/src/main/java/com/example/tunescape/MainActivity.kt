package com.example.tunescape

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tunescape.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var musicAdapter: MusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestRuntimePermission()
        setTheme(R.style.Theme_TuneScape)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for Nav Drawer
        toggle = ActionBarDrawerToggle(this,binding.root,R.string.open,R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val musicList = ArrayList<String>()
        musicList.add("First Song")
        musicList.add("Second Song")
        musicList.add("Third Song")
        musicList.add("Fourth Song")
        musicList.add("Fifth Song")
        musicList.add("Sixth Song")

        binding.musicRV.setHasFixedSize(true)
        binding.musicRV.setItemViewCacheSize(12)
        binding.musicRV.layoutManager=LinearLayoutManager(this)
        musicAdapter = MusicAdapter(this,musicList)
        binding.musicRV.adapter = musicAdapter

        binding.shuffleBtn.setOnClickListener {
            val intent = Intent(this,PlayerActivity::class.java)
            startActivity(intent)
        }
        binding.favouriteBtn.setOnClickListener {
            val intent = Intent(this,FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.playlistBtn.setOnClickListener {
            val intent = Intent(this,PlaylistActivity::class.java)
            startActivity(intent)
        }

        binding.navView.setNavigationItemSelectedListener{
            when (it.itemId){

                R.id.navFeedback -> Toast.makeText(this,"Feedback",Toast.LENGTH_SHORT).show()
                R.id.navSettings -> Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show()
                R.id.navAbout -> Toast.makeText(this,"About",Toast.LENGTH_SHORT).show()
                R.id.navExit -> exitProcess(1)


            }
            true
        }




    }

    private fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==13){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}