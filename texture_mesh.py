import pymeshlab
ms = pymeshlab.MeshSet()
print('loading rasters')
ms.load_project('images/focus_simplified.mlp')
print('-rasters loaded')

print('loading mesh')
ms.load_new_mesh('1.ply')
print('-mesh loaded')

print('starting texturing')
ms.compute_texcoord_parametrization_and_texture_from_registered_rasters(texturesize=4096 ,colorcorrection=True)
print('-texturing done')


ms.save_current_mesh('out.ply')
