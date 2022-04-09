package com.example.profile.activities.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.R
import com.example.profile.databinding.*
import com.example.profile.models.UserDetails
import com.example.profile.utils.hideView
import com.example.profile.utils.showView
import com.squareup.picasso.Picasso
import java.util.*

class MainAdapter(
    private val context: Context,
    private var profile: List<String>,
    private var user: UserDetails
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ProfileItems(val id: Int) {
        NAME(1),
        PHOTO(2),
        GENDER(3),
        ABOUT(4),
        SCHOOL(5),
        HOBBIES(6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            ProfileItems.NAME.id -> NameViewHolder(
                NameLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ProfileItems.PHOTO.id -> PhotoViewHolder(
                PhotoLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ProfileItems.GENDER.id -> GenderViewHolder(
                GenderLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ProfileItems.ABOUT.id -> AboutViewHolder(
                AboutLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ProfileItems.SCHOOL.id -> SchoolViewHolder(
                SchoolLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ProfileItems.HOBBIES.id -> HobbiesViewHolder(
                HobbiesLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NameViewHolder -> holder.bind(user.name)
            is PhotoViewHolder -> holder.bind(user.photo)
            is AboutViewHolder -> holder.bind(user.about)
            is SchoolViewHolder -> holder.bind(user.school)
            is GenderViewHolder -> holder.bind(context, user.gender)
            is HobbiesViewHolder -> holder.bind(user.hobbies)
            else -> throw IllegalArgumentException("Unknown view holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (profile[position]) {
            ProfileItems.NAME.name.lowercase(Locale.getDefault()) -> ProfileItems.NAME.id
            ProfileItems.PHOTO.name.lowercase(Locale.getDefault()) -> ProfileItems.PHOTO.id
            ProfileItems.GENDER.name.lowercase(Locale.getDefault()) -> ProfileItems.GENDER.id
            ProfileItems.ABOUT.name.lowercase(Locale.getDefault()) -> ProfileItems.ABOUT.id
            ProfileItems.SCHOOL.name.lowercase(Locale.getDefault()) -> ProfileItems.SCHOOL.id
            ProfileItems.HOBBIES.name.lowercase(Locale.getDefault()) -> ProfileItems.HOBBIES.id
            else -> throw IllegalArgumentException("Unknown User detail")
        }
    }

    override fun getItemCount() = profile.size

    fun updateItems(nextUser: UserDetails) {
        user = nextUser
        notifyDataSetChanged()
    }

    class NameViewHolder(private val binding: NameLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profileName: String?) {
            if (profileName.isNullOrEmpty()) {
                with(binding) {
                    root.hideView()
                    name.hideView()
                }
                return
            }
            binding.root.showView()
            with(binding.name) {
                text = profileName
                showView()
            }
        }
    }

    class PhotoViewHolder(private val binding: PhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: String?) {
            if (photo.isNullOrEmpty()) {
                with(binding) {
                    root.hideView()
                    thumb.hideView()
                }
                return
            }
            Picasso.get().load(photo).into(binding.thumb)

            with(binding) {
                root.showView()
                thumb.showView()
            }
        }
    }

    class GenderViewHolder(private val binding: GenderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, userGender: String?) {
            if (userGender.isNullOrEmpty()) {
                with(binding) {
                    root.hideView()
                    gender.hideView()
                }
                return
            }

            binding.root.showView()
            with(binding.gender) {
                showView()
                text = if (userGender.lowercase(Locale.getDefault()) == "m")
                    context.getString(R.string.male)
                else
                    context.getString(R.string.female)
            }
        }
    }

    class AboutViewHolder(private val binding: AboutLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userAbout: String?) {
            if (userAbout.isNullOrEmpty()) {
                with(binding) {
                    root.hideView()
                    about.hideView()
                }
                return
            }

            binding.root.showView()
            with(binding.about) {
                text = userAbout
                showView()
            }
        }
    }

    class SchoolViewHolder(private val binding: SchoolLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userSchool: String?) {
            if (userSchool.isNullOrEmpty()) {
                with(binding) {
                    root.hideView()
                    school.hideView()
                }
                return
            }

            binding.root.showView()
            with(binding.school) {
                showView()
                text = userSchool
            }

        }
    }

    class HobbiesViewHolder(private val binding: HobbiesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hobbies: List<String>) {
            if (hobbies.isEmpty()) {
                with(binding) {
                    root.hideView()
                    rvHobby.hideView()
                }
                return
            }
            binding.root.showView()
            with(binding.rvHobby) {
                adapter = HobbyAdapter(hobbies)
                showView()
            }
        }

        inner class HobbyAdapter(private val hobbies: List<String>) :
            RecyclerView.Adapter<HobbyAdapter.HobbyViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): HobbyAdapter.HobbyViewHolder {
                val binding =
                    HobbyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HobbyViewHolder(binding)
            }

            override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
                holder.bind(hobbies[position])
            }

            override fun getItemCount() = hobbies.size

            inner class HobbyViewHolder(private val binding: HobbyItemBinding) :
                RecyclerView.ViewHolder(binding.root) {

                fun bind(hobbyName: String) {
                    binding.hobbyName.text = hobbyName
                }
            }

        }
    }
}
