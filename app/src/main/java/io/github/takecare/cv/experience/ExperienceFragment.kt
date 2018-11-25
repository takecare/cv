package io.github.takecare.cv.experience

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.fragment_experience.*
import javax.inject.Inject

class ExperienceFragment : Fragment(), ExperienceView {

    companion object {
        fun newInstance() = ExperienceFragment()
    }

    @Inject
    lateinit var presenter: ExperiencePresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        DaggerExperienceComponent.builder()
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
//        recyclerview.adapter =
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.stopPresenting()
    }

    override fun show(experienceViewModel: ExperienceViewModel) {
        // TODO: show not implemented
    }

    override fun showError(throwable: Throwable) {
        // TODO: showError not implemented
    }
}
